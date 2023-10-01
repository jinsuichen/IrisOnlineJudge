package fun.icpc.iris.irisonlinejudge.commons.exception.irisexception;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;

public class AuthSystemException extends BaseIrisException {
    @Override
    public String getMessage() {
        return "Authorization system error";
    }
}
