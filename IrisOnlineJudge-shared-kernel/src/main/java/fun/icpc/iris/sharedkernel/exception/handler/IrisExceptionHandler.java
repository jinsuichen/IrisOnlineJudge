package fun.icpc.iris.sharedkernel.exception.handler;

import fun.icpc.iris.sharedkernel.exception.BaseIrisException;
import fun.icpc.iris.sharedkernel.exception.irisexception.NoAuthException;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
import jakarta.servlet.http.HttpServletResponse;
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

    /**
     * Handle NoAuthException.
     * Set the status code to 401.
     *
     * @param e the NoAuthException
     * @return the Iris response
     */
    @ExceptionHandler(NoAuthException.class)
    public ResponseEntity<IrisMessage<Void>> handleNoAuthException(NoAuthException e) {
        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                .body(IrisMessageFactory.fail(e.getMessage()));
    }

}
