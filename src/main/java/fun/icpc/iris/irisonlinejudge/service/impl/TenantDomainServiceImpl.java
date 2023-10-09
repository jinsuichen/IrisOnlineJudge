package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.context.UserContext;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.dto.TenantDTO;
import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.TenantEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.TenantPurchasePlanEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.UserEntity;
import fun.icpc.iris.irisonlinejudge.repo.TenantRepository;
import fun.icpc.iris.irisonlinejudge.service.domainservice.MpTenantUserDomainService;
import fun.icpc.iris.irisonlinejudge.service.domainservice.TenantDomainService;
import fun.icpc.iris.irisonlinejudge.service.domainservice.TenantPurchasePlanDomainService;
import fun.icpc.iris.irisonlinejudge.service.domainservice.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantDomainServiceImpl implements TenantDomainService {

    private final TenantRepository tenantRepository;

    private final MpTenantUserDomainService mpTenantUserDomainService;

    private final TenantPurchasePlanDomainService tenantPurchasePlanDomainService;

    private final UserDomainService userDomainService;


    @Override
    @Transactional
    public IrisMessage<Long> createTenant(TenantDTO tenantDTO, Long purchasePlanId) {
        UserDTO userDTO = UserContext.get();
        Long userId = userDTO.getId();

        // Check if the tenant name already exists.
        if(tenantRepository.existsByName(tenantDTO.getName())){
            return IrisMessageFactory.fail("The tenant name already exists.");
        }

        IrisMessage<UserEntity> user = userDomainService.getUser(userId);
        if(!user.success()){
            return IrisMessageFactory.fail(user.message());
        }
        UserEntity userEntity = user.data();

        // Get purchase plan.
        IrisMessage<TenantPurchasePlanEntity> tenantPurchasePlan =
                tenantPurchasePlanDomainService.getTenantPurchasePlan(purchasePlanId);
        if (!tenantPurchasePlan.success()) {
            return IrisMessageFactory.fail(tenantPurchasePlan.message());
        }
        TenantPurchasePlanEntity tenantPurchasePlanEntity = tenantPurchasePlan.data();

        // Create tenant.
        Duration duration = tenantPurchasePlanEntity.getDuration();
        LocalDateTime expirationTime = LocalDateTime.now().plus(duration);

        TenantEntity tenantEntity = TenantEntity.builder()
                .name(tenantDTO.getName())
                .description(tenantDTO.getDescription())
                .memberLimit(tenantPurchasePlanEntity.getMemberLimit())
                .commitLimit(tenantPurchasePlanEntity.getCommitLimit())
                .expirationTime(expirationTime)
                .build();

        tenantEntity = tenantRepository.save(tenantEntity);

        // Add current user as the owner of the tenant, and has all permissions.
        mpTenantUserDomainService.addOwner(userEntity, tenantEntity);

        return IrisMessageFactory.success(tenantEntity.getId());
    }

    @Override
    public IrisMessage<TenantEntity> getTenant(Long tenantId) {
        Optional<TenantEntity> tenant = tenantRepository.findById(tenantId);
        return tenant.map(IrisMessageFactory::success)
                .orElseGet(() -> IrisMessageFactory.fail("The tenant does not exist."));
    }
}
