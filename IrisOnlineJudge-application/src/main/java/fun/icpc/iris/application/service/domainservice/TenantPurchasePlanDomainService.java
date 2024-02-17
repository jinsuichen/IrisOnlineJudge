package fun.icpc.iris.application.service.domainservice;

import fun.icpc.iris.domain.entity.table.TenantPurchasePlanEntity;
import fun.icpc.iris.sharedkernel.util.IrisMessage;

public interface TenantPurchasePlanDomainService {

    /**
     * Get the purchase plan by id.
     *
     * @param tenantPurchasePlanId The id of the purchase plan.
     * @return The purchase plan.
     */
    IrisMessage<TenantPurchasePlanEntity> getTenantPurchasePlan(Long tenantPurchasePlanId);

}
