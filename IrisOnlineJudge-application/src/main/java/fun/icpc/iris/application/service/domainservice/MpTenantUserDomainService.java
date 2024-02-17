package fun.icpc.iris.application.service.domainservice;

import fun.icpc.iris.domain.entity.table.TenantEntity;
import fun.icpc.iris.domain.entity.table.UserEntity;
import fun.icpc.iris.sharedkernel.enums.TenantUserRoleTypeEnum;
import fun.icpc.iris.sharedkernel.util.IrisMessage;

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

    /**
     * Add owner to tenant
     *
     * @param userEntity   userEntity
     * @param tenantEntity tenantEntity
     * @return is success
     */
    IrisMessage<Boolean> addOwner(UserEntity userEntity, TenantEntity tenantEntity);


}
