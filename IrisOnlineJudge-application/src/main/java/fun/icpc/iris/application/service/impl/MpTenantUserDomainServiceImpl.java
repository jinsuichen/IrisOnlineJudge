package fun.icpc.iris.application.service.impl;

import fun.icpc.iris.application.service.domainservice.MpTenantUserDomainService;
import fun.icpc.iris.domain.entity.mapping.MpTenantUser;
import fun.icpc.iris.domain.entity.table.TenantEntity;
import fun.icpc.iris.domain.entity.table.UserEntity;
import fun.icpc.iris.domain.repository.MpTenantUserRepository;
import fun.icpc.iris.sharedkernel.enums.TenantUserRoleTypeEnum;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MpTenantUserDomainServiceImpl implements MpTenantUserDomainService {

    private final MpTenantUserRepository mpTenantUserRepository;

    @Override
    public IrisMessage<List<TenantUserRoleTypeEnum>> getTenantUserRole(Long userId, Long tenantId) {
        List<MpTenantUser> mpTenantUserList = mpTenantUserRepository.findByTenant_IdAndUser_Id(tenantId, userId);
        List<TenantUserRoleTypeEnum> ret = mpTenantUserList.stream()
                .map(MpTenantUser::getRole)
                .toList();
        return IrisMessageFactory.success(ret);
    }

    @Override
    public IrisMessage<Boolean> addOwner(UserEntity userEntity, TenantEntity tenantEntity) {

        List<MpTenantUser> list = Arrays.stream(TenantUserRoleTypeEnum.values())
                .map(role -> MpTenantUser.builder()
                        .user(userEntity)
                        .tenant(tenantEntity)
                        .role(role)
                        .build())
                .toList();

        mpTenantUserRepository.saveAll(list);

        return IrisMessageFactory.success(true);
    }
}