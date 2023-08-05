
package fun.icpc.iris.irisonlinejudge.service.judge.run;


import fun.icpc.iris.irisonlinejudge.domain.dto.RunningResultDTO;

public interface SandboxRunner {

    RunningResultDTO doRun(String stdInputContent, String codeContent, Long memoryLimit);

}
