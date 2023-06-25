package fun.icpc.iris.irisonlinejudge.commons.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fun.icpc.iris.irisonlinejudge.commons.utils.IrisResponse;
import fun.icpc.iris.irisonlinejudge.commons.utils.IrisResponseFactory;

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
