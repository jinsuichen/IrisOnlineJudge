package fun.icpc.iris.sharedkernel.exception.irisexception;

import fun.icpc.iris.sharedkernel.exception.BaseIrisException;

/**
 * Throw this exception when the sandbox runner has an error.
 */
public class SandboxRunnerException extends BaseIrisException {
    @Override
    public String getMessage() {
        return "Sandbox runner error!";
    }
}
