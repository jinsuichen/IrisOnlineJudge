package fun.icpc.iris.irisonlinejudge.rest.controller;

import fun.icpc.iris.irisonlinejudge.commons.util.ConstraintValidator;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.dto.ProblemDTO;
import fun.icpc.iris.irisonlinejudge.domain.dto.TestCaseDTO;
import fun.icpc.iris.irisonlinejudge.domain.enums.GlobalUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.enums.TenantUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.record.CreateProblemRequest;
import fun.icpc.iris.irisonlinejudge.service.aop.AuthGlobal;
import fun.icpc.iris.irisonlinejudge.service.aop.AuthTenant;
import fun.icpc.iris.irisonlinejudge.service.applicationservice.ProblemApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/problem")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemApplicationService problemApplicationService;

    /**
     * Create a problem
     *
     * @param request  request body
     * @param tenantId tenant id
     * @return problem id
     */
    @PostMapping("/{tenantId}/set")
    @AuthGlobal(GlobalUserRoleTypeEnum.USER)
    @AuthTenant(TenantUserRoleTypeEnum.PROBLEM_AUTHOR)
    public IrisMessage<Long> createProblem(@RequestBody CreateProblemRequest request,
                                           @PathVariable Long tenantId) {
        ProblemDTO problemDTO = request.problemDTO();
        List<TestCaseDTO> testCaseDTOS = request.testCaseDTOList();

        // Check problemDTO
        IrisMessage<Boolean> problemValid = ConstraintValidator.validateProblem(problemDTO);
        if (!problemValid.success()) {
            return IrisMessageFactory.fail(problemValid.message());
        }

        // Check testCaseDTOList
        for (TestCaseDTO testCaseDTO : testCaseDTOS) {
            IrisMessage<Boolean> testCaseValid = ConstraintValidator.validateTestCase(testCaseDTO);
            if (!testCaseValid.success()) {
                return IrisMessageFactory.fail(testCaseValid.message());
            }
        }

        return problemApplicationService.addCompleteProblem(problemDTO, testCaseDTOS, tenantId);
    }


}
