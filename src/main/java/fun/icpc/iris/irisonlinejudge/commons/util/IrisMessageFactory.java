package fun.icpc.iris.irisonlinejudge.commons.util;

/**
 * The factory of IrisResponse, which is used to create different IrisResponse.
 */
public class IrisMessageFactory {
    /**
     * Create a successful result.
     *
     * @param data The data of the result.
     * @param <T>  The type of data.
     * @return The result.
     */
    public static <T> IrisMessage<T> success(T data) {
        return new IrisMessage<>(true, null, data);
    }

    /**
     * Create a successful result without data.
     *
     * @return The result.
     */
    public static IrisMessage<Void> success() {
        return new IrisMessage<>(true, null, null);
    }

    /**
     * Create a failed result.
     *
     * @param message The message of the result.
     * @param <T>     The type of data.
     * @return The result.
     */
    public static <T> IrisMessage<T> fail(String message) {
        return new IrisMessage<>(false, message, null);
    }
}
