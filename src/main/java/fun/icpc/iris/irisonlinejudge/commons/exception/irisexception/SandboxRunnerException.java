package fun.icpc.iris.irisonlinejudge.commons.exception.irisexception;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;

/**
 * Throw this exception when the sandbox runner has an error.
 */
public class SandboxRunnerException extends BaseIrisException {
    @Override
    public String getMessage() {
        return "Sandbox runner error!";
    }
}
