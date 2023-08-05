package fun.icpc.iris.irisonlinejudge.service.judge.run;

import fun.icpc.iris.irisonlinejudge.domain.dto.RunningResultDTO;

public interface TimeLimitedSandboxRunner extends SandboxRunner {

    default RunningResultDTO doRun(String stdInputContent, String codeContent, Long memoryLimit) {
        return doRun(stdInputContent, codeContent, memoryLimit, 2000L);
    }

    RunningResultDTO doRun(String stdInputContent, String codeContent, Long memoryLimit, Long timeLimit);

}
