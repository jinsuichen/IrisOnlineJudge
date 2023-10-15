package fun.icpc.iris.irisonlinejudge.domain.dto;

import fun.icpc.iris.irisonlinejudge.domain.enums.CheckerTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.enums.ProblemDifficultyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDTO {

    private Long id;

    private CheckerTypeEnum checkerType;

    private String content;

    private Long memoryLimit;

    private Long timeLimit;

    private String title;

    private ProblemDifficultyTypeEnum difficulty;

    private Long creatorId;

    private Boolean isPublic;

}
