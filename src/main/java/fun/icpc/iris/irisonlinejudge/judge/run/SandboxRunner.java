
package fun.icpc.iris.irisonlinejudge.judge.run;


import fun.icpc.iris.irisonlinejudge.judge.run.docker.runner.RunningResult;

public interface SandboxRunner {

    RunningResult doRun(String stdInputContent, String codeContent, Long memoryLimit);

}
