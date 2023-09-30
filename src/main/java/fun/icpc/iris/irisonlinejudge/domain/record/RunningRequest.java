package fun.icpc.iris.irisonlinejudge.domain.record;

import lombok.Builder;

@Builder
public record RunningRequest(String code, String input) {
}
