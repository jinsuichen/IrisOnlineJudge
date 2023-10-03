package fun.icpc.iris.irisonlinejudge.service;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.enums.TenantUserRoleTypeEnum;

import java.util.List;

public interface MpTenantUserDomainService {

    /**
     * Get the tenant role of the user.
     *
     * @param userId   The id of the user.
     * @param tenantId The id of the tenant.
     * @return The tenant role of the user.
     */
    IrisMessage<List<TenantUserRoleTypeEnum>> getTenantUserRole(Long userId, Long tenantId);
}
