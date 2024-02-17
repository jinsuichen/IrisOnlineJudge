package fun.icpc.iris.sharedkernel.exception.irisexception;

import fun.icpc.iris.sharedkernel.exception.BaseIrisException;

public class AuthSystemException extends BaseIrisException {
    @Override
    public String getMessage() {
        return "Authorization system error";
    }
}
