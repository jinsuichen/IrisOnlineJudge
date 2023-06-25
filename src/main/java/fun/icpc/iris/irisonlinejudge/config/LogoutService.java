package fun.icpc.iris.irisonlinejudge.config;


import java.util.Objects;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import fun.icpc.iris.irisonlinejudge.commons.redis.RedisConstantsUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final StringRedisTemplate stringRedisTemplate;
    private final JwtService jwtService;

    /**
     * logout
     *
     * @param request
     * @param response
     * @param authentication with <b>refresh</> token
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            return;
        }
        String jwtToken = authHeader.substring(7);
        String handle = jwtService.extractUsername(jwtToken);

        // delete the refresh token
        String redisKey = RedisConstantsUtils.jwtRefreshToken(handle);
        stringRedisTemplate.delete(redisKey);
    }
}
