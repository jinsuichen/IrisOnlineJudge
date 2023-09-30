package fun.icpc.iris.irisonlinejudge.service;

import fun.icpc.iris.irisonlinejudge.domain.enums.ExecCommandTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.record.RunningResult;

public interface JudgeService {

    /**
     * Run code with default MemoryLimit and TimeLimit
     *
     * @param stdInputContent     stdin
     * @param codeContent         code
     * @param execCommandTypeEnum command type
     * @return result
     */
    RunningResult run(String stdInputContent, String codeContent, ExecCommandTypeEnum execCommandTypeEnum);

    /**
     * Run code with custom MemoryLimit and TimeLimit
     *
     * @param stdInputContent     stdin
     * @param codeContent         code
     * @param execCommandTypeEnum command type
     * @param memoryLimit         memory limit(MB)
     * @param timeLimit           time limit(ms)
     * @return result
     */
    RunningResult run(String stdInputContent, String codeContent, ExecCommandTypeEnum execCommandTypeEnum, Long memoryLimit, Long timeLimit);

}
