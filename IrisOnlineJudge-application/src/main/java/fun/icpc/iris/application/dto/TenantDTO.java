package fun.icpc.iris.application.dto;
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

    private Long memberLimit;

    private Long commitLimit;
}

