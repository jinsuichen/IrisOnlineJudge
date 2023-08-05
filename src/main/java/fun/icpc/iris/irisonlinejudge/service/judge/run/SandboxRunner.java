
package fun.icpc.iris.irisonlinejudge.service.judge.run;


import fun.icpc.iris.irisonlinejudge.service.judge.run.docker.runner.RunningResult;

public interface SandboxRunner {

    RunningResult doRun(String stdInputContent, String codeContent, Long memoryLimit);

}
