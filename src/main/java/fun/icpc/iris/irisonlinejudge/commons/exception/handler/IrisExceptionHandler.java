package fun.icpc.iris.irisonlinejudge.commons.exception.handler;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The Iris exception handler, which handles all the exceptions thrown by Iris Online Judge.
 */
@ControllerAdvice
public class IrisExceptionHandler {

    /**
     * Handle Iris base exception.
     *
     * @param e the Iris base exception
     * @return the Iris response
     */
    @ExceptionHandler(BaseIrisException.class)
    public ResponseEntity<IrisMessage<Void>> handleIrisBaseException(BaseIrisException e) {
        return ResponseEntity.badRequest().body(IrisMessageFactory.fail(e.getMessage()));
    }

}
