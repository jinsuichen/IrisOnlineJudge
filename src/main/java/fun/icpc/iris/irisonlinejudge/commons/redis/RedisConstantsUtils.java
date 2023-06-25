package fun.icpc.iris.irisonlinejudge.commons.redis;

/**
 * The constants of Redis keys.
 */
public class RedisConstantsUtils {

    /**
     * refresh token key
     *
     * @param handle user's handle
     * @return key
     */
    public static String jwtRefreshToken(String handle) {
        return "iris:jwt:refresh:token:" + handle;
    }

}
