package fun.icpc.iris.irisonlinejudge.commons.exception.irisexception;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SystemException extends BaseIrisException {

    protected String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
