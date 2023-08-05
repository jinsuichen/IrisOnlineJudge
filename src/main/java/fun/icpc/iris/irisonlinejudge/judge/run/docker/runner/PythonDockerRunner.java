package fun.icpc.iris.irisonlinejudge.judge.run.docker.runner;

import com.github.dockerjava.core.command.ExecStartResultCallback;
import fun.icpc.iris.irisonlinejudge.commons.exception.SandboxRunnerException;
import fun.icpc.iris.irisonlinejudge.commons.utils.TarUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class PythonDockerRunner extends AbstractDockerRunner {


    @Override
    protected RunningResult runDocker(String stdInputContent, String codeContent, String containerId) {

        InputStream inputStream;

        try {
            TarUtils.TarEntry codeEntry = TarUtils.TarEntry.builder().filename("code.py").content(codeContent).build();
            TarUtils.TarEntry inputEntry = TarUtils.TarEntry.builder().filename("input.txt").content(stdInputContent).build();

            inputStream = TarUtils.buildTarInputStream(List.of(codeEntry, inputEntry));

        } catch (Exception e) {
            throw new SandboxRunnerException();
        }

        // Copy code and input to container
        dockerClient.copyArchiveToContainerCmd(containerId)
                .withTarInputStream(inputStream)
                .exec();

        // Running code in container
        String id = dockerClient.execCreateCmd(containerId)
                .withAttachStdout(true)
                .withCmd("sh", "-c", "python3 /code.py < /input.txt")
                .exec()
                .getId();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            dockerClient.execStartCmd(id).exec(new ExecStartResultCallback(outputStream, null)).awaitCompletion();
        } catch (InterruptedException e) {
            throw new SandboxRunnerException();
        }

        return new RunningResult(outputStream.toString(), "", 0);
    }
}
