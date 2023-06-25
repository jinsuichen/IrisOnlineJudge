package fun.icpc.iris.irisonlinejudge.auth;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fun.icpc.iris.irisonlinejudge.commons.exception.DuplicateRegistrationException;
import fun.icpc.iris.irisonlinejudge.commons.redis.RedisConstantsUtils;
import fun.icpc.iris.irisonlinejudge.config.JwtService;
import fun.icpc.iris.irisonlinejudge.user.Role;
import fun.icpc.iris.irisonlinejudge.user.User;
import fun.icpc.iris.irisonlinejudge.user.UserRepository;
import lombok.RequiredArgsConstructor;


/**
 * The service for authentication.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StringRedisTemplate redisTemplate;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    /**
     * user register, if the handle is already registered, throw {@link DuplicateRegistrationException}
     *
     * @param request {@link RegisterRequest}
     * @return {@link AuthenticationResponse}
     */
    public AuthenticationResponse register(RegisterRequest request) {

        if (repository.existsByHandle(request.getHandle())) {
            throw new DuplicateRegistrationException();
        }

        User user = User.builder()
                .handle(request.getHandle())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        // save the refresh token after md5 to redis
        String redisKey = RedisConstantsUtils.jwtRefreshToken(user.getHandle());
        String redisValue = DigestUtils.md5Hex(refreshToken);
        redisTemplate.opsForValue().set(redisKey, redisValue, refreshExpiration, TimeUnit.MILLISECONDS);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    /**
     * authenticate
     *
     * @param request {@link AuthenticationRequest}
     * @return {@link AuthenticationResponse}
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getHandle(),
                        request.getPassword()
                )
        );
        // if the authentication is successful, the user must exist
        User user = repository.findByHandle(request.getHandle()).orElseThrow();

        // generate jwt token and refresh token
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        // save the refresh token after md5 to redis
        String redisKey = RedisConstantsUtils.jwtRefreshToken(user.getHandle());
        String redisValue = DigestUtils.md5Hex(refreshToken);
        redisTemplate.opsForValue().set(redisKey, redisValue, refreshExpiration, TimeUnit.MILLISECONDS);

        // return the response
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    /**
     * refresh token
     *
     * @param refreshToken the refresh token
     * @return {@link AuthenticationResponse}
     */
    public AuthenticationResponse refreshToken(String refreshToken) {
        String handle = jwtService.extractUsername(refreshToken);
        if (Objects.isNull(handle)) {
            throw new IllegalArgumentException("refresh token is invalid");
        }
        // if the refresh token is valid, generate a new access token
        User user = this.repository.findByHandle(handle).orElseThrow();
        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new IllegalArgumentException("refresh token is invalid");
        }

        // check if the refresh token is out of date
        String redisKey = RedisConstantsUtils.jwtRefreshToken(handle);
        String expectedRedisValue = DigestUtils.md5Hex(refreshToken);
        String actualRedisValue = redisTemplate.opsForValue().get(redisKey);
        if (!expectedRedisValue.equals(actualRedisValue)) {
            throw new IllegalArgumentException("refresh token is out of date");
        }

        // generate a new pair of tokens and save refresh one to redis
        String accessToken = jwtService.generateToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);
        String newRedisValue = DigestUtils.md5Hex(newRefreshToken);
        redisTemplate.opsForValue().set(redisKey, newRedisValue, refreshExpiration, TimeUnit.MILLISECONDS);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

}