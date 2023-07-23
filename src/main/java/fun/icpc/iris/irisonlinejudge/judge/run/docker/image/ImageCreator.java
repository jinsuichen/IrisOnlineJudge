package fun.icpc.iris.irisonlinejudge.judge.run.docker.image;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.model.Image;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


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


    /**
     * Check if judger image exist
     *
     * @return true if exist
     */
    public boolean isImageExist() {
        List<Image> images = dockerClient.listImagesCmd().withImageNameFilter("iris-judger").exec();
        return CollectionUtils.isNotEmpty(images);
    }


}
