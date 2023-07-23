
package fun.icpc.iris.irisonlinejudge.judge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.icpc.iris.irisonlinejudge.judge.run.docker.runner.PythonDockerRunner;
import fun.icpc.iris.irisonlinejudge.judge.run.docker.runner.RunningResult;

@Service
public class JudgeService {

    @Autowired
    private PythonDockerRunner pythonDockerRunner;


    public JudgeResult judgePython(String code, String input) {
        RunningResult runningResult = pythonDockerRunner.doRun(input, code, 100000000L);
        System.out.println(runningResult.output());
        return new JudgeResult(JudgeResultTypeEnum.ACCEPT, runningResult.output());
    }

}
