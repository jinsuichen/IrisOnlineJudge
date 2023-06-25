
package fun.icpc.iris.irisonlinejudge.commons.exception;

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
