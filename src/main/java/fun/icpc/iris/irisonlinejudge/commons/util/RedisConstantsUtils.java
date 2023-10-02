package fun.icpc.iris.irisonlinejudge.commons.util;

import java.time.Duration;

/**
 * The constants of Redis keys.
 */
public class RedisConstantsUtils {

    public static final Duration USER_LOGIN_EXPIRE_TIME = Duration.ofDays(7);

    /**
     * The key of the user's login information.
     *
     * @param token The token of the user.
     * @return The key of the user's login information.
     */
    public static String userLoginKey(String token) {
        return "iris:user:login:" + token;
    }

    public static String userSaltKey(String handle) {
        return "iris:user:salt:" + handle;
    }
}
