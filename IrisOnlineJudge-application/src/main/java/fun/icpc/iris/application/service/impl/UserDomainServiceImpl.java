package fun.icpc.iris.application.service.impl;

import fun.icpc.iris.application.UserContext;
import fun.icpc.iris.application.service.domainservice.UserDomainService;
import fun.icpc.iris.domain.entity.table.UserEntity;
import fun.icpc.iris.domain.repository.UserRepository;
import fun.icpc.iris.sharedkernel.enums.GlobalUserRoleTypeEnum;
import fun.icpc.iris.sharedkernel.exception.irisexception.NoSuchUserException;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;

    @Override
    public IrisMessage<Boolean> changeNickName(String newNickName) {
        Long userId = UserContext.get().getId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(NoSuchUserException::new);
        userEntity.setNickName(newNickName);
        userRepository.save(userEntity);

        return IrisMessageFactory.success(true);
    }

    @Override
    public IrisMessage<Boolean> changeAvatar(String newAvatar) {
        return null;
    }

    @Override
    public IrisMessage<GlobalUserRoleTypeEnum> getGlobalUserRole(String handle) {
        Optional<UserEntity> optionalUser = userRepository.findByHandle(handle);
        return optionalUser.map(userEntity -> IrisMessageFactory.success(userEntity.getRole()))
                .orElseGet(() -> IrisMessageFactory.fail("User not found."));
    }

    @Override
    public IrisMessage<Void> updateUnfreezeTime(Long userId, LocalDateTime unfreezeTime) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if(Objects.isNull(userEntity)) {
            return IrisMessageFactory.fail("User not found.");
        }
        userEntity.setUnfreezeTime(unfreezeTime);
        userRepository.save(userEntity);
        return IrisMessageFactory.success();
    }

    @Override
    public IrisMessage<UserEntity> getUser(Long userId) {
        Optional<UserEntity> user =  userRepository.findById(userId);
        return user.map(IrisMessageFactory::success)
                .orElseGet(() -> IrisMessageFactory.fail("User not found."));
    }
}