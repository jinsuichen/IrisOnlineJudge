package fun.icpc.iris.irisonlinejudge.commons;

import lombok.Data;

@Data
public class Result<T> {
    private final boolean success;
    private final String message;
    private final T data;

    private Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, null, data);
    }

    public static Result<Void> success() {
        return new Result<>(true, null, null);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }
}
