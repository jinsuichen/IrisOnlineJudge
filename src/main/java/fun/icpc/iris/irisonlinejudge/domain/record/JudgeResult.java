package fun.icpc.iris.irisonlinejudge.domain.record;

import fun.icpc.iris.irisonlinejudge.domain.enums.JudgeResultTypeEnum;
import lombok.Builder;

@Builder
public record JudgeResult(JudgeResultTypeEnum type, String compileMessage, String stdOutput, String stdError) {
}
