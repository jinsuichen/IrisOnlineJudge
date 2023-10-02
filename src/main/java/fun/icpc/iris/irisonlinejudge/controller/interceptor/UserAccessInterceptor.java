package fun.icpc.iris.irisonlinejudge.controller.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import fun.icpc.iris.irisonlinejudge.commons.context.UserContext;
import fun.icpc.iris.irisonlinejudge.commons.util.JsonUtils;
import fun.icpc.iris.irisonlinejudge.commons.util.RedisConstantsUtils;
import fun.icpc.iris.irisonlinejudge.domain.converter.UserConverter;
import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;
import java.util.Optional;

/**
 * For all users' requests:
 * 1. Refresh the token expire time.
 * 2. Set user info to UserContext.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserAccessInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    private final UserConverter userConverter;

    private final String BEARER_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) throws JsonProcessingException {

        Optional<String> tokenOption = extractToken(request);
        if(tokenOption.isEmpty()) {
            // The user is not logged in.
            return true;
        }

        // Get user info from redis.
        String token = tokenOption.get();
        String userJson = stringRedisTemplate.opsForValue().get(token);
        if(Objects.isNull(userJson)) {
            log.info("Token {} is invalid", token);
            return true;
        }
        UserDTO user = JsonUtils.fromJson(userJson, UserDTO.class);

        // Set user info to UserContext.
        UserContext.setUser(user, token);
        // Refresh token expire time.
        stringRedisTemplate.expire(
                RedisConstantsUtils.userLoginKey(token),
                RedisConstantsUtils.USER_LOGIN_EXPIRE_TIME);

        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler, Exception ex) {
        UserContext.removeUser();
    }

    private Optional<String> extractToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        if(Objects.isNull(authorization) || !authorization.startsWith(BEARER_PREFIX)) {
            return Optional.empty();
        }

        authorization = authorization.substring(BEARER_PREFIX.length());
        return Optional.of(authorization);
    }
}
