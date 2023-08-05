package fun.icpc.iris.irisonlinejudge.service.judge.run.docker.runner;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.HostConfig;
import fun.icpc.iris.irisonlinejudge.service.judge.run.SandboxRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public abstract class AbstractDockerRunner implements SandboxRunner {

    @Autowired
    protected DockerClient dockerClient;


    /**
     * Run the code in the container.
     *
     * @param stdInputContent standard input content
     * @param codeContent     code content
     * @param memoryLimit     memory limit in megabytes
     * @return standard output content
     */
    public RunningResult doRun(String stdInputContent, String codeContent, Long memoryLimit) {
        String container = createContainer(memoryLimit);
        RunningResult runningResult = runDocker(stdInputContent, codeContent, container);
        destroyContainer(container);
        return runningResult;
    }


    /**
     * Run the code in the container with default memory limit.
     *
     * @param stdInputContent standard input content
     * @param codeContent     code content
     * @return standard output content
     */
    public RunningResult doRun(String stdInputContent, String codeContent) {
        return doRun(stdInputContent, codeContent, 256L);
    }


    /**
     * Create a container with memory limit and other default properties.
     *
     * @param memoryLimit memory limit in megabytes
     */
    protected String createContainer(Long memoryLimit) {

        HostConfig hostConfig = HostConfig.newHostConfig()
                .withMemory(memoryLimit * 1024 * 1024)
                .withMemorySwap(0L);

        CreateContainerResponse containerResponse = dockerClient.createContainerCmd("iris-judger:latest")
                .withHostConfig(hostConfig)
                .withCmd("tail", "-f", "/dev/null")
                .exec();

        String containerId = containerResponse.getId();

        dockerClient.startContainerCmd(containerId).exec();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return containerId;
    }


    /**
     * Run the code in the container.
     *
     * @param stdInputContent standard input content
     * @param codeContent     code content
     * @return standard output content
     */
    protected abstract RunningResult runDocker(String stdInputContent, String codeContent, String containerId);


    /**
     * Destroy the container.
     */
    protected void destroyContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
        dockerClient.removeContainerCmd(containerId).exec();
    }

}
