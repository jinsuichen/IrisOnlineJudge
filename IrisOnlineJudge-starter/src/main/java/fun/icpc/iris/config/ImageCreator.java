package fun.icpc.iris.config;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;


@Component
@Slf4j
public class ImageCreator {

    @Autowired
    private DockerClient dockerClient;

    /**
     * Create image for judger
     *
     * @return image id
     */
    @SneakyThrows
    public String createImage() {

        BuildImageCmd buildImageCmd = dockerClient.buildImageCmd()
                .withDockerfile(new ClassPathResource("judger/Dockerfile").getFile())
                .withTags(new HashSet<>(Collections.singletonList("iris-judger:latest")));

        return buildImageCmd.exec(new BuildImageResultCallback()).awaitImageId();

    }
}
