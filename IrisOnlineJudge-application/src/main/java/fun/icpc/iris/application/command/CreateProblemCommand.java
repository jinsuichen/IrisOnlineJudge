package fun.icpc.iris.application.command;
import fun.icpc.iris.application.dto.ProblemDTO;
import fun.icpc.iris.application.dto.TestCaseDTO;

import java.util.List;

public record CreateProblemCommand(

        ProblemDTO problemDTO,

        List<TestCaseDTO> testCaseDTOList

) {
}
