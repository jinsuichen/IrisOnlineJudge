package fun.icpc.iris.irisonlinejudge.domain.dto;

/**
 * The result of running code
 *
 * @param output   stdout
 * @param error    stderr
 * @param exitCode exit code
 */
public record RunningResultDTO(String output, String error, int exitCode) {
}
