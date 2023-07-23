package fun.icpc.iris.irisonlinejudge.judge.run;

import fun.icpc.iris.irisonlinejudge.judge.run.docker.runner.RunningResult;

public interface TimeLimitedSandboxRunner extends SandboxRunner {

    default RunningResult doRun(String stdInputContent, String codeContent, Long memoryLimit) {
        return doRun(stdInputContent, codeContent, memoryLimit, 2000L);
    }

    RunningResult doRun(String stdInputContent, String codeContent, Long memoryLimit, Long timeLimit);

}
