
package fun.icpc.iris.sharedkernel.exception;

/**
 * The base exception to Iris.
 */
public abstract class BaseIrisException extends RuntimeException {


    /**
     * The message of the exception.
     *
     * @return The message.
     */
    @Override
    public abstract String getMessage();

}
