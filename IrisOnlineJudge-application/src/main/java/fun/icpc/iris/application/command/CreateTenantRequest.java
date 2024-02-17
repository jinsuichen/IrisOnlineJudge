package fun.icpc.iris.application.command;

import fun.icpc.iris.application.dto.TenantDTO;

public record CreateTenantRequest (
        TenantDTO tenantDTO,
        Long purchasePlanId
){
}
