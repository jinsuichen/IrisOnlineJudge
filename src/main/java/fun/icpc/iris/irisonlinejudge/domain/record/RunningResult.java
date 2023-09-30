package fun.icpc.iris.irisonlinejudge.domain.record;

import lombok.Builder;

@Builder
public record RunningResult(
        String renameExitCode,
        String compileInfo,
        String compileExitCode,
        String stdout,
        String stderr,
        String exitCode) {
}
