package fun.icpc.iris.sharedkernel.exception.irisexception;

import fun.icpc.iris.sharedkernel.exception.BaseIrisException;

public class NoSuchProblemException extends BaseIrisException {
    @Override
    public String getMessage() {
        return "No such problem!";
    }
}