package fun.icpc.iris.irisonlinejudge.judge.run.docker.image;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * Build docker image for judger when application starts
 */
@Component
@RequiredArgsConstructor
@DependsOn("imageCreator")
@Slf4j
public class ImageCreationRunner implements ApplicationRunner {

    private final ImageCreator imageCreator;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Building image... It may take 5-10 minutes, please wait.");

        // Thread for logging, print elapsed time every second
        Thread loggingThread = new Thread(() -> {
            long startTimeStamp = System.currentTimeMillis();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    long currentTimeStamp = System.currentTimeMillis();
                    long elapsedTime = currentTimeStamp - startTimeStamp;
                    long minutes = elapsedTime / 1000 / 60;
                    long seconds = elapsedTime / 1000 % 60;
                    log.info("Building image... Elapsed time: {}m {}s", minutes, seconds);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        loggingThread.start();

        String imageId = imageCreator.createImage();

        log.info("Image built successfully. Image id: {}", imageId);

        loggingThread.interrupt();
    }
}
