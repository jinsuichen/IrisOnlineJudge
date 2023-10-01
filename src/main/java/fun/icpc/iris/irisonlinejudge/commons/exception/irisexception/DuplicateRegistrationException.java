package fun.icpc.iris.irisonlinejudge.commons.exception.irisexception;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;

/**
 * Throw this exception when a user tries to register with a handle that already exists.
 */
public class DuplicateRegistrationException extends BaseIrisException {

    @Override
    public String getMessage() {
        return "Duplicate registration! Please log in directly or change your handle.";
    }

}
