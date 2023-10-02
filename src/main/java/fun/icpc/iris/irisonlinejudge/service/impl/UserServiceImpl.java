package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.context.UserContext;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.SystemException;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.commons.util.JsonUtils;
import fun.icpc.iris.irisonlinejudge.commons.util.RedisConstantsUtils;
import fun.icpc.iris.irisonlinejudge.domain.converter.UserConverter;
import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.UserEntity;
import fun.icpc.iris.irisonlinejudge.domain.enums.RoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.repo.UserRepository;
import fun.icpc.iris.irisonlinejudge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.codec.digest.DigestUtils.md5;


/**
 * The service for authentication.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StringRedisTemplate stringRedisTemplate;
    private final UserConverter userConverter;

    private final SecureRandom random = new SecureRandom();

    @SneakyThrows
    @Override
    @Transactional
    public IrisMessage<Boolean> register(String handle, String nickName, String password) {
        if(userRepository.existsByHandle(handle)) {
            return IrisMessageFactory.fail("Handle already exists.");
        }

        // Save user to database.
        String hashPassword = hashPasswordWithRandSalt(password, handle);
        UserEntity user = UserEntity.builder()
                .handle(handle)
                .nickName(nickName)
                .password(hashPassword)
                .role(RoleTypeEnum.USER)
                .build();
        user = userRepository.save(user);

        // Save user to redis.
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(
                RedisConstantsUtils.userLoginKey(token),
                JsonUtils.toJson(user),
                RedisConstantsUtils.USER_LOGIN_EXPIRE_TIME);

        return IrisMessageFactory.success(true);
    }

    @SneakyThrows
    @Override
    public IrisMessage<String> login(String handle, String password) {
        Optional<UserEntity> optionalUser = userRepository.findByHandle(handle);
        if(optionalUser.isEmpty()) {
            return IrisMessageFactory.fail("User not found.");
        }

        // Check password.
        UserEntity user = optionalUser.get();
        String hashPassword = hashPasswordWithRedisSalt(password, handle);
        if(!user.getPassword().equals(hashPassword)) {
            return IrisMessageFactory.fail("Handle or password is incorrect.");
        }

        // Check freeze time.
        if(user.getUnfreezeTime().isAfter(LocalDateTime.now())) {
            return IrisMessageFactory.fail("Your account is frozen.");
        }

        // Save user to redis.
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(
                RedisConstantsUtils.userLoginKey(token),
                JsonUtils.toJson(user),
                RedisConstantsUtils.USER_LOGIN_EXPIRE_TIME);

        return IrisMessageFactory.success(token);
    }

    @Override
    public IrisMessage<Boolean> logout() {
        String userToken = UserContext.getUserToken();
        stringRedisTemplate.delete(RedisConstantsUtils.userLoginKey(userToken));
        return IrisMessageFactory.success(true);
    }

    @Override
    public IrisMessage<Boolean> checkLogin() {
        String userToken = UserContext.getUserToken();
        Boolean exist = stringRedisTemplate.hasKey(RedisConstantsUtils.userLoginKey(userToken));
        return IrisMessageFactory.success(exist);
    }

    @Override
    public IrisMessage<Boolean> changePassword(String oldPassword, String newPassword) {
        UserDTO userDTO = UserContext.getUser();
        UserEntity entity = userConverter.toEntity(userDTO);

        // Check old password.
        String hashOldPassword = hashPasswordWithRedisSalt(oldPassword, entity.getHandle());
        if(!entity.getPassword().equals(hashOldPassword)) {
            return IrisMessageFactory.fail("Old password is incorrect.");
        }

        // Change password.
        String hashNewPassword = hashPasswordWithRandSalt(newPassword, entity.getHandle());
        entity.setPassword(hashNewPassword);
        userRepository.save(entity);

        return IrisMessageFactory.success(true);
    }

    @Override
    public IrisMessage<Boolean> changeNickName(String newNickName) {
        UserEntity userEntity = userConverter.toEntity(UserContext.getUser());
        userEntity.setNickName(newNickName);
        userRepository.save(userEntity);

        return IrisMessageFactory.success(true);
    }

    @Override
    public IrisMessage<Boolean> changeAvatar(String newAvatar) {
        return null;
    }


    private String hashPasswordWithRandSalt(String password, String handle) {
        // Generate a 128-bit (16-byte) salt.
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);

        stringRedisTemplate.opsForValue().set(
                RedisConstantsUtils.userSaltKey(handle),
                Base64.getEncoder().encodeToString(saltBytes));

        byte[] hashPassword = BCrypt.generate(md5(password), saltBytes, 10);
        return Base64.getEncoder().encodeToString(hashPassword);
    }

    private String hashPasswordWithRedisSalt(String password, String handle) {
        // Get salt from Redis.
        String saltBytesStr = stringRedisTemplate.opsForValue().get(RedisConstantsUtils.userSaltKey(handle));
        if(StringUtils.isEmpty(saltBytesStr)) {
            log.warn("Salt not found.");
            throw new SystemException("Salt not found.");
        }

        byte[] saltBytes = Base64.getDecoder().decode(saltBytesStr);

        byte[] hashPassword = BCrypt.generate(md5(password), saltBytes, 10);
        return Base64.getEncoder().encodeToString(hashPassword);
    }


}