package fun.icpc.iris.irisonlinejudge.commons.utils;

/**
 * The response of a request.
 * Any request will return a result Object.
 *
 * @param <T>     The type of data.
 * @param success Whether the request is successful.
 * @param message The message of the result.
 * @param data    The data of the result.
 */
public record IrisResponse<T>(boolean success, String message, T data) {
}
