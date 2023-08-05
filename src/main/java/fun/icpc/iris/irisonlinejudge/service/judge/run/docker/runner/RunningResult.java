package fun.icpc.iris.irisonlinejudge.service.judge.run.docker.runner;

/**
 * The result of running code
 *
 * @param output   stdout
 * @param error    stderr
 * @param exitCode exit code
 */
public record RunningResult(String output, String error, int exitCode) {
}
