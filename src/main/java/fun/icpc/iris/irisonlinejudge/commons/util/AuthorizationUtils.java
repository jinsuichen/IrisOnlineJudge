package fun.icpc.iris.irisonlinejudge.commons.util;

import jakarta.servlet.http.HttpServletRequest;
import org.bouncycastle.crypto.generators.BCrypt;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class AuthorizationUtils {

    private static final String BEARER_PREFIX = "Bearer ";

    public static Optional<String> extractToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        if(Objects.isNull(authorization) || !authorization.startsWith(BEARER_PREFIX)) {
            return Optional.empty();
        }

        authorization = authorization.substring(BEARER_PREFIX.length());
        return Optional.of(authorization);
    }

    public static String hashPassword(String password) {
        // Generate salt
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[128];
        random.nextBytes(saltBytes);

        byte[] hashPassword = BCrypt.generate(password.getBytes(), saltBytes, 10);
        return Arrays.toString(hashPassword);
    }
}
