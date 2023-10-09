package fun.icpc.iris.irisonlinejudge.service.domainservice;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.dto.TenantDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.TenantEntity;

public interface TenantDomainService {

    /**
     * Create a tenant.
     * The tenant will be created with a purchase plan.
     * Current user will be the owner of the tenant, and has all permissions.
     *
     * @param tenantDTO      The tenant to create.
     * @param purchasePlanId The purchase plan id.
     * @return The result.
     */
    IrisMessage<Long> createTenant(TenantDTO tenantDTO, Long purchasePlanId);

    /**
     * Get the tenant by id.
     *
     * @param tenantId The id of the tenant.
     * @return The tenant.
     */
    IrisMessage<TenantEntity> getTenant(Long tenantId);

}
