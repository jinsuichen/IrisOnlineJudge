package fun.icpc.iris.irisonlinejudge.service.judge.run.docker.runner;


import fun.icpc.iris.irisonlinejudge.domain.dto.RunningResultDTO;

public class CppDockerRunner extends AbstractDockerRunner {

    @Override
    protected RunningResultDTO runDocker(String stdInputContent, String codeContent, String containerId) {
        return null;
    }
}
