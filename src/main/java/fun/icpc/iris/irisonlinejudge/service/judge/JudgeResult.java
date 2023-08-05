package fun.icpc.iris.irisonlinejudge.service.judge;

import lombok.Builder;

@Builder
public record JudgeResult(JudgeResultTypeEnum type, String message) {
}
