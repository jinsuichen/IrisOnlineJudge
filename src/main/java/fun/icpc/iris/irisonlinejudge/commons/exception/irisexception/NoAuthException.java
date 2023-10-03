package fun.icpc.iris.irisonlinejudge.commons.exception.irisexception;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;

public class NoAuthException extends BaseIrisException {
    @Override
    public String getMessage() {
        return "No auth.";
    }
}
