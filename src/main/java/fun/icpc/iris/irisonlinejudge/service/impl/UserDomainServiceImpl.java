package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.context.UserContext;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.converter.UserConverter;
import fun.icpc.iris.irisonlinejudge.domain.entity.UserEntity;
import fun.icpc.iris.irisonlinejudge.domain.enums.GlobalUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.enums.TenantUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.repo.UserRepository;
import fun.icpc.iris.irisonlinejudge.service.MpTenantUserDomainService;
import fun.icpc.iris.irisonlinejudge.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final MpTenantUserDomainService mpTenantUserDomainService;

    @Override
    public IrisMessage<Boolean> changeNickName(String newNickName) {
        UserEntity userEntity = userConverter.toEntity(UserContext.get());
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
    public IrisMessage<List<TenantUserRoleTypeEnum>> getTenantUserRole(Long handle, Long tenantId) {
        return mpTenantUserDomainService.getTenantUserRole(handle, tenantId);
    }
}
