package fun.icpc.iris.irisonlinejudge.domain.dto;

import fun.icpc.iris.irisonlinejudge.domain.enums.JudgeResultTypeEnum;
import lombok.Builder;

@Builder
public record JudgeResultDTO(JudgeResultTypeEnum type, String compileMessage, String stdOutput, String stdError) {
}
