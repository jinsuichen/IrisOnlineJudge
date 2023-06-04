package fun.icpc.iris.irisonlinejudge.commons;

import lombok.Data;

/**
 * The result of a request.
 * Any request will return a result Object.
 * @param <T> The type of data.
 */
@Data
public class Result<T> {

    /**
     * Whether the request is successful.
     */
    private final boolean success;

    /**
     * The message of the result.
     */
    private final String message;

    /**
     * The data of the result.
     */
    private final T data;

    /**
     * Constructor.
     * @param success Whether the request is successful.
     * @param message The message of the result.
     * @param data The data of the result.
     */
    private Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * Create a successful result.
     * @param data The data of the result.
     * @return The result.
     * @param <T> The type of data.
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(true, null, data);
    }

    /**
     * Create a successful result without data.
     * @return The result.
     */
    public static Result<Void> success() {
        return new Result<>(true, null, null);
    }

    /**
     * Create a failed result.
     * @param message The message of the result.
     * @return The result.
     * @param <T> The type of data.
     */
    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }
}
