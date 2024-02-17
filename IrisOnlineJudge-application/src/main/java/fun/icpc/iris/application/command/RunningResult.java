package fun.icpc.iris.application.command;

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
