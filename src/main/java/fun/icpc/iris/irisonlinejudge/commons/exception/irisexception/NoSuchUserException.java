package fun.icpc.iris.irisonlinejudge.commons.exception.irisexception;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;

/**
 * Throw this exception when there is no such user.
 */
public class NoSuchUserException extends BaseIrisException {
    @Override
    public String getMessage() {
        return "No such user!";
    }
}
