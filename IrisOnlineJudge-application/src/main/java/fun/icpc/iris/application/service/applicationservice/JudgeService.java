package fun.icpc.iris.application.service.applicationservice;

import fun.icpc.iris.application.command.RunningResult;
import fun.icpc.iris.sharedkernel.enums.ExecCommandTypeEnum;

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
