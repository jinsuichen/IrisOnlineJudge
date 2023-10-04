package fun.icpc.iris.irisonlinejudge.domain.converter;

import fun.icpc.iris.irisonlinejudge.domain.dto.TenantDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.TenantEntity;

public class TenantConverter {

    public static TenantDTO toDTO(TenantEntity tenantEntity) {
        return TenantDTO.builder()
                .id(tenantEntity.getId())
                .name(tenantEntity.getName())
                .description(tenantEntity.getDescription())
                .commitLimit(tenantEntity.getCommitLimit())
                .memberLimit(tenantEntity.getMemberLimit())
                .build();
    }
}
