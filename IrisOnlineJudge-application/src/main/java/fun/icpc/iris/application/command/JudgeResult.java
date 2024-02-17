package fun.icpc.iris.application.command;
import fun.icpc.iris.sharedkernel.enums.JudgeResultTypeEnum;
import lombok.Builder;

@Builder
public record JudgeResult(JudgeResultTypeEnum type, String compileMessage, String stdOutput, String stdError) {
}
