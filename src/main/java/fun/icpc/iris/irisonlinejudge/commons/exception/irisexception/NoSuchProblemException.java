package fun.icpc.iris.irisonlinejudge.commons.exception.irisexception;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;

public class NoSuchProblemException extends BaseIrisException {
    @Override
    public String getMessage() {
        return "No such problem!";
    }
}
