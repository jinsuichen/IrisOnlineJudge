package fun.icpc.iris.irisonlinejudge.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CopyArchiveFromContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.SandboxRunnerException;
import fun.icpc.iris.irisonlinejudge.commons.util.TarUtils;
import fun.icpc.iris.irisonlinejudge.domain.enums.ExecCommandTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.record.RunningResult;
import fun.icpc.iris.irisonlinejudge.service.JudgeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {

    private final DockerClient dockerClient;

    private final ThreadPoolTaskExecutor dockerRunnerThreadPool;

    private final ThreadPoolTaskExecutor dockerDestroyerThreadPool;

    private final Long defaultMemoryLimit = 256L;
    private final Long defaultTimeLimit = 2L;
    private final String renameExitCodeContainerPath = "/mv_exit_code";
    private final String compileInfoContainerPath = "/compile_info";
    private final String compileExitCodeContainerPath = "/compile_exit_code";
    private final String outputContainerPath = "/stdout";
    private final String errorContainerPath = "/stderr";
    private final String exitCodeContainerPath = "/process_exit_code";

    @Override
    public RunningResult run(String stdInputContent, String codeContent, ExecCommandTypeEnum execCommandTypeEnum) {
        return this.run(stdInputContent, codeContent, execCommandTypeEnum, defaultMemoryLimit, defaultTimeLimit);
    }

    @Override
    public RunningResult run(String stdInputContent, String codeContent, ExecCommandTypeEnum execCommandTypeEnum,
                             Long memoryLimit, Long timeLimit) {
        String containerId = createContainer(defaultMemoryLimit);
        Future<RunningResult> runningResultFuture = dockerRunnerThreadPool.submit(
                () -> runDocker(stdInputContent, codeContent, containerId, execCommandTypeEnum, timeLimit)
        );

        final long mostWaitTime = timeLimit * 2;

        try {
            return runningResultFuture.get(mostWaitTime, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            throw new SandboxRunnerException();
        } finally {
            dockerDestroyerThreadPool.execute(() -> destroyContainer(containerId));
        }
    }


    /**
     * Create container
     * Set memory limit and memory swap limit.
     * The memory swap limit is set to 0 to prevent the container from using swap.
     * Set the command to tail -f /dev/null to prevent the container from exiting immediately after startup.
     * Start the container.
     *
     * @param memoryLimit memory limit(MB)
     * @return container id
     */
    private String createContainer(Long memoryLimit) {

        HostConfig hostConfig = HostConfig.newHostConfig()
                .withMemory(memoryLimit * 1024 * 1024)
                .withMemorySwap(0L);

        CreateContainerResponse containerResponse = dockerClient.createContainerCmd("iris-judger:latest")
                .withHostConfig(hostConfig)
                .withCmd("tail", "-f", "/dev/null")
                .exec();

        String containerId = containerResponse.getId();

        dockerClient.startContainerCmd(containerId).exec();

        return containerId;
    }

    /**
     * Run code in container
     * The main steps are as follows:
     * 1. Copy code and input to container.
     * 2. Run code in container.
     * 3. Copy output, error and exit code from container.
     * 4. Return result.
     *
     * @param stdInputContent     stdin for code
     * @param codeContent         code to run
     * @param containerId         container id
     * @param execCommandTypeEnum command type
     * @param timeLimit           time limit(ms)
     * @return result
     */
    private RunningResult runDocker(String stdInputContent, String codeContent, String containerId,
                                    ExecCommandTypeEnum execCommandTypeEnum, Long timeLimit) {

        InputStream inputStream;

        try {
            TarUtils.TarEntry codeEntry = TarUtils.TarEntry.builder().filename("code").content(codeContent).build();
            TarUtils.TarEntry inputEntry = TarUtils.TarEntry.builder().filename("input").content(stdInputContent).build();

            inputStream = TarUtils.buildTarInputStream(List.of(codeEntry, inputEntry));

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandboxRunnerException();
        }

        // Copy code and input to container
        dockerClient.copyArchiveToContainerCmd(containerId)
                .withTarInputStream(inputStream)
                .exec();

        // Running code in container
        String id = dockerClient.execCreateCmd(containerId)
                .withAttachStdout(true)
                .withCmd("sh", "-c", execCommandTypeEnum.getCommand(String.valueOf(timeLimit)))
                .exec()
                .getId();

        try {
            dockerClient.execStartCmd(id).exec(new ExecStartResultCallback(null, null)).awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new SandboxRunnerException();
        }

        return RunningResult.builder()
                .renameExitCode(getContainerOutput(containerId, renameExitCodeContainerPath))
                .compileInfo(getContainerOutput(containerId, compileInfoContainerPath))
                .compileExitCode(getContainerOutput(containerId, compileExitCodeContainerPath))
                .stdout(getContainerOutput(containerId, outputContainerPath))
                .stderr(getContainerOutput(containerId, errorContainerPath))
                .exitCode(getContainerOutput(containerId, exitCodeContainerPath))
                .build();
    }


    /**
     * Destroy container
     * Stop container and remove container.
     *
     * @param containerId container id
     */
    private void destroyContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
        dockerClient.removeContainerCmd(containerId).exec();
    }

    /**
     * Get container output
     * Copy file from container to host.
     *
     * @param containerId   container id
     * @param containerPath container path to copy
     * @return container output
     */
    @SneakyThrows
    private String getContainerOutput(String containerId, String containerPath) {
        CopyArchiveFromContainerCmd copyCmd = dockerClient.copyArchiveFromContainerCmd(containerId, containerPath);
        InputStream inputStream = copyCmd.exec();
        TarArchiveInputStream tarInputStream = new TarArchiveInputStream(inputStream);
        TarArchiveEntry entry = tarInputStream.getNextTarEntry();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(tarInputStream, outputStream);
        return outputStream.toString(StandardCharsets.UTF_8);
    }
}
