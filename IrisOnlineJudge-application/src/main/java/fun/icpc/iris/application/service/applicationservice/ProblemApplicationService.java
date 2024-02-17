package fun.icpc.iris.application.service.applicationservice;

import fun.icpc.iris.application.dto.ProblemDTO;
import fun.icpc.iris.application.dto.TestCaseDTO;
import fun.icpc.iris.sharedkernel.util.IrisMessage;

import java.util.List;

public interface ProblemApplicationService {

    /**
     * Add a complete problem (with basic info & test cases)
     *
     * @param problemDTO      problemDTO
     * @param testCaseDTOList testCaseDTOList
     * @param tenantId        tenantId
     * @return problem id
     */
    IrisMessage<Long> addCompleteProblem(ProblemDTO problemDTO,
                                         List<TestCaseDTO> testCaseDTOList,
                                         Long tenantId);

    /**
     * Add a problem with basic info
     *
     * @param problemDTO problemDTO
     * @param tenantId   tenantId
     * @return problem id
     */
    IrisMessage<Long> addProblemWithBasicInfo(ProblemDTO problemDTO, Long tenantId);

    /**
     * Add a test case to a problem
     *
     * @param testCaseDTO testCaseDTO
     * @return test case id
     */
    IrisMessage<Long> addTestCaseToProblem(TestCaseDTO testCaseDTO);

}
