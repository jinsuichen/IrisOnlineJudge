package fun.icpc.iris.irisonlinejudge.judge;

import lombok.Builder;

@Builder
public record JudgeResult(JudgeResultTypeEnum type, String message) {
}
