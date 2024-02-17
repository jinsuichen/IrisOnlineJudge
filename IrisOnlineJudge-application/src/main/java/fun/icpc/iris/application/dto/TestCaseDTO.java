package fun.icpc.iris.application.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDTO {

    private Long id;

    private String input;

    private String output;

    private Long problemId;

    private Boolean isPublic;

    private Integer score;

    private Boolean isSample;

    private Long creatorId;
}
