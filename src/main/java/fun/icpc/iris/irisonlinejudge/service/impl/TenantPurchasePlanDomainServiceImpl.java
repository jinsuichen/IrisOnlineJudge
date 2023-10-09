package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.TenantPurchasePlanEntity;
import fun.icpc.iris.irisonlinejudge.repo.TenantPurchasePlanRepository;
import fun.icpc.iris.irisonlinejudge.service.domainservice.TenantPurchasePlanDomainService;
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
