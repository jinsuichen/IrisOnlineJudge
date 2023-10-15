package fun.icpc.iris.irisonlinejudge.domain.record;

import fun.icpc.iris.irisonlinejudge.domain.dto.ProblemDTO;
import fun.icpc.iris.irisonlinejudge.domain.dto.TestCaseDTO;

import java.util.List;

public record CreateProblemRequest(

        ProblemDTO problemDTO,

        List<TestCaseDTO> testCaseDTOList

) {
}
