package fun.icpc.iris.irisonlinejudge.judge.run.docker.runner;

import java.io.InputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.ExecCreateCmd;
import com.github.dockerjava.api.model.Frame;

@Service
public class PythonDockerRunner extends AbstractDockerRunner {


    @Override
    protected RunningResult runDocker(String stdInputContent, String codeContent, String containerId) {

        // Get input stream of code and input
        InputStream codeInputStream = IOUtils.toInputStream(codeContent, "UTF-8");
        InputStream inputInputStream = IOUtils.toInputStream(stdInputContent, "UTF-8");


        TarArchiveEntry codeTarEntry = new TarArchiveEntry("code.py");
        TarArchiveEntry inputTarEntry = new TarArchiveEntry("input.txt");

        codeTarEntry.setSize(codeContent.length());
        inputTarEntry.setSize(stdInputContent.length());

        TarArchiveInputStream codeTarInputStream = new TarArchiveInputStream();

        // TODO get tar


        // Copy code and input to container
        dockerClient.copyArchiveToContainerCmd(containerId)
                .withTarInputStream(codeTarInputStream)
                .withRemotePath("/")
                .exec();
        dockerClient.copyArchiveToContainerCmd(containerId)
                .withTarInputStream(inputTarInputStream)
                .withRemotePath("/")
                .exec();


        // Running code in container
        ExecCreateCmd runCmd = dockerClient.execCreateCmd(containerId)
                .withAttachStdout(true)
                .withCmd("python", "/code.py", "<", "/input.txt");
        String runId = runCmd.exec().getId();


        StringBuilder output = new StringBuilder();

        try {
            dockerClient.logContainerCmd(runId)
                    .withStdOut(true)
                    .withFollowStream(true)
                    .withTailAll()
                    .exec(new ResultCallback.Adapter<>() {
                        @Override
                        public void onNext(Frame frame) {
                            output.append(new String(frame.getPayload()));
                        }
                    }).awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new RunningResult(output.toString(), "", 0);
    }

}
