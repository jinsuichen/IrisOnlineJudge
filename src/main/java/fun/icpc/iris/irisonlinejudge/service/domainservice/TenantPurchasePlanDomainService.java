package fun.icpc.iris.irisonlinejudge.service.domainservice;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.TenantPurchasePlanEntity;

public interface TenantPurchasePlanDomainService {

    /**
     * Get the purchase plan by id.
     *
     * @param tenantPurchasePlanId The id of the purchase plan.
     * @return The purchase plan.
     */
    IrisMessage<TenantPurchasePlanEntity> getTenantPurchasePlan(Long tenantPurchasePlanId);

}
