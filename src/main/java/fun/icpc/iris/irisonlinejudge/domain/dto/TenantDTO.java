package fun.icpc.iris.irisonlinejudge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantDTO {

    private Long id;

    private String name;

    private String description;

    private Long commitLimit;

    private Long memberLimit;
}

