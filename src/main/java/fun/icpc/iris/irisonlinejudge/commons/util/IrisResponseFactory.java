package fun.icpc.iris.irisonlinejudge.commons.util;

/**
 * The factory of IrisResponse, which is used to create different IrisResponse.
 */
public class IrisResponseFactory {
    /**
     * Create a successful result.
     *
     * @param data The data of the result.
     * @param <T>  The type of data.
     * @return The result.
     */
    public static <T> IrisResponse<T> success(T data) {
        return new IrisResponse<>(true, null, data);
    }

    /**
     * Create a successful result without data.
     *
     * @return The result.
     */
    public static IrisResponse<Void> success() {
        return new IrisResponse<>(true, null, null);
    }

    /**
     * Create a failed result.
     *
     * @param message The message of the result.
     * @param <T>     The type of data.
     * @return The result.
     */
    public static <T> IrisResponse<T> fail(String message) {
        return new IrisResponse<>(false, message, null);
    }
}
