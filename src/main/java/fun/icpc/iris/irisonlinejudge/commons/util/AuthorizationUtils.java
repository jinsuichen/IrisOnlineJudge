package fun.icpc.iris.irisonlinejudge.commons.util;

import jakarta.servlet.http.HttpServletRequest;

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
}
