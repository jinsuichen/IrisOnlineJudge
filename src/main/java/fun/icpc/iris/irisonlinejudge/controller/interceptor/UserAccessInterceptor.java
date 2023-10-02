package fun.icpc.iris.irisonlinejudge.controller.interceptor;

import fun.icpc.iris.irisonlinejudge.commons.context.UserContext;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.AuthSystemException;
import fun.icpc.iris.irisonlinejudge.commons.util.JsonUtils;
import fun.icpc.iris.irisonlinejudge.commons.util.RedisConstantsUtils;
import fun.icpc.iris.irisonlinejudge.domain.converter.UserConverter;
import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;
import fun.icpc.iris.irisonlinejudge.domain.record.LoginToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
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
            @NonNull Object handler) {

        Optional<String> optionalToken = extractToken(request);
        if(optionalToken.isEmpty()) {
            // The user is not logged in.
            return true;
        }

        // Get user info from redis.
        Optional<LoginToken> optionalLoginToken = LoginToken.fromContent(optionalToken.get());
        if(optionalLoginToken.isEmpty()) {
            // The token is invalid.
            log.info("User token is invalid.");
            return true;
        }

        LoginToken token = optionalLoginToken.get();
        Boolean hasUUID = stringRedisTemplate.opsForSet().isMember(
                RedisConstantsUtils.userLoginUUIDKey(token.handler()),
                token.loginUUID());
        if(BooleanUtils.isNotTrue(hasUUID)) {
            // User is not logged in. (Incorrect loginUUID)
            log.info("User {} loginUUID is incorrect.", token.handler());
            return true;
        }

        // User is logged in.
        String userJson = stringRedisTemplate.opsForValue().get(
                RedisConstantsUtils.userInfoKey(token.handler()));
        UserDTO user = JsonUtils.fromJson(userJson, UserDTO.class);
        if(Objects.isNull(user)) {
            throw new AuthSystemException();
        }
        user.setLoginUUID(token.loginUUID());

        // Set user info to UserContext.
        UserContext.set(user);
        // Refresh token expire time.
        stringRedisTemplate.expire(
                RedisConstantsUtils.userLoginUUIDKey(token.handler()),
                RedisConstantsUtils.USER_LOGIN_EXPIRE_TIME);
        stringRedisTemplate.expire(
                RedisConstantsUtils.userInfoKey(token.handler()),
                RedisConstantsUtils.USER_LOGIN_EXPIRE_TIME);

        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler, Exception ex) {
        UserContext.remove();
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
