package fun.icpc.iris.irisonlinejudge.commons.util;

import org.apache.commons.lang3.BooleanUtils;

/**
 * The response of a request.
 * Any request will return a result Object.
 *
 * @param <T>     The type of data.
 * @param success Whether the request is successful.
 * @param message The message of the result.
 * @param data    The data of the result.
 */
public record IrisMessage<T>(boolean success, String message, T data) {
    public boolean isSuccess() {
        return BooleanUtils.isTrue(success);
    }

    public boolean isFail() {
        return BooleanUtils.isNotTrue(success);
    }
}
