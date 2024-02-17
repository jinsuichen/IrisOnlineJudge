package fun.icpc.iris.sharedkernel.exception.irisexception;

import fun.icpc.iris.sharedkernel.exception.BaseIrisException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SystemException extends BaseIrisException {

    protected String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
