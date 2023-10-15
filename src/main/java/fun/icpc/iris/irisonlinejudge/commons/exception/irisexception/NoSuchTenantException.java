package fun.icpc.iris.irisonlinejudge.commons.exception.irisexception;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;

public class NoSuchTenantException extends BaseIrisException {
    @Override
    public String getMessage() {
        return "No such tenant!";
    }
}
