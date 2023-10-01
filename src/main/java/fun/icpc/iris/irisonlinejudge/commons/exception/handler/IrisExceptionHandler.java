package fun.icpc.iris.irisonlinejudge.commons.exception.handler;

import fun.icpc.iris.irisonlinejudge.commons.exception.BaseIrisException;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisResponse;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisResponseFactory;
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
    public ResponseEntity<IrisResponse<Void>> handleIrisBaseException(BaseIrisException e) {
        return ResponseEntity.badRequest().body(IrisResponseFactory.fail(e.getMessage()));
    }

}
