package fun.icpc.iris.application.dto;

import fun.icpc.iris.sharedkernel.enums.CheckerTypeEnum;
import fun.icpc.iris.sharedkernel.enums.ProblemDifficultyTypeEnum;
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
