package fun.icpc.iris.irisonlinejudge.domain.record;

import fun.icpc.iris.irisonlinejudge.domain.dto.TenantDTO;

public record CreateTenantRequest (
        TenantDTO tenantDTO,
        Long purchasePlanId
){
}
