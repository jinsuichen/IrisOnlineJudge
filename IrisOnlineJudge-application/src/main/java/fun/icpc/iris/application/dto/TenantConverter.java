package fun.icpc.iris.application.dto;

import fun.icpc.iris.domain.entity.table.TenantEntity;

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
