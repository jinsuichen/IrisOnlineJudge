package fun.icpc.iris.adapter.rest.controller;

import fun.icpc.iris.application.ConstraintValidator;
import fun.icpc.iris.application.command.CreateProblemCommand;
import fun.icpc.iris.application.dto.ProblemDTO;
import fun.icpc.iris.application.dto.TestCaseDTO;
import fun.icpc.iris.application.service.aop.AuthGlobal;
import fun.icpc.iris.application.service.aop.AuthTenant;
import fun.icpc.iris.application.service.applicationservice.ProblemApplicationService;
import fun.icpc.iris.sharedkernel.enums.GlobalUserRoleTypeEnum;
import fun.icpc.iris.sharedkernel.enums.TenantUserRoleTypeEnum;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
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
    public IrisMessage<Long> createProblem(@RequestBody CreateProblemCommand request,
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
