package fun.icpc.iris.application.service.impl;

import fun.icpc.iris.application.service.domainservice.TenantPurchasePlanDomainService;
import fun.icpc.iris.domain.entity.table.TenantPurchasePlanEntity;
import fun.icpc.iris.domain.repository.TenantPurchasePlanRepository;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantPurchasePlanDomainServiceImpl implements TenantPurchasePlanDomainService {

    private final TenantPurchasePlanRepository tenantPurchasePlanRepository;

    @Override
    public IrisMessage<TenantPurchasePlanEntity> getTenantPurchasePlan(Long tenantPurchasePlanId) {
        Optional<TenantPurchasePlanEntity> optionalTenantPurchasePlanEntity =
                tenantPurchasePlanRepository.findById(tenantPurchasePlanId);
        return optionalTenantPurchasePlanEntity.map(IrisMessageFactory::success)
                .orElseGet(() -> IrisMessageFactory.fail("The purchase plan does not exist."));
    }
}
