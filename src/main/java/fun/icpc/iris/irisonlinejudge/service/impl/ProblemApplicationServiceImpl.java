package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.dto.ProblemDTO;
import fun.icpc.iris.irisonlinejudge.domain.dto.TestCaseDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpTenantProblem;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.ProblemEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.TestCaseEntity;
import fun.icpc.iris.irisonlinejudge.service.applicationservice.ProblemApplicationService;
import fun.icpc.iris.irisonlinejudge.service.domainservice.MpTenantProblemDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemApplicationServiceImpl implements ProblemApplicationService {

    private final ProblemDomainServiceImpl problemDomainService;

    private final TestCaseDomainServiceImpl testCaseDomainService;

    private final MpTenantProblemDomainService mpTenantProblemDomainService;

    @Override
    @Transactional
    public IrisMessage<Long> addCompleteProblem(ProblemDTO problemDTO,
                                                        List<TestCaseDTO> testCaseDTOList,
                                                        Long tenantId) {
        // Add problem
        IrisMessage<ProblemEntity> problem = problemDomainService.addProblemWithBasicInfo(problemDTO);
        if (!problem.success()) {
            return IrisMessageFactory.fail(problem.message());
        }

        ProblemEntity problemEntity = problem.data();

        // Add testCase to problem
        for (TestCaseDTO testCaseDTO : testCaseDTOList) {
            testCaseDTO.setProblemId(problemEntity.getId());
            IrisMessage<TestCaseEntity> message = testCaseDomainService.addTestCase(testCaseDTO);
            if (!message.success()) {
                return IrisMessageFactory.fail(message.message());
            }
        }

        // Add problem to tenant
        IrisMessage<MpTenantProblem> addProblemToTenant =
                mpTenantProblemDomainService.addProblemToTenant(tenantId, problemEntity.getId());
        if (!addProblemToTenant.success()) {
            return IrisMessageFactory.fail(addProblemToTenant.message());
        }

        return IrisMessageFactory.success(problemEntity.getId());
    }

    @Override
    @Transactional
    public IrisMessage<Long> addProblemWithBasicInfo(ProblemDTO problemDTO, Long tenantId) {
        // Add problem
        IrisMessage<ProblemEntity> problem = problemDomainService.addProblemWithBasicInfo(problemDTO);
        if (!problem.success()) {
            return IrisMessageFactory.fail(problem.message());
        }

        ProblemEntity problemEntity = problem.data();

        // Add problem to tenant
        IrisMessage<MpTenantProblem> addProblemToTenant =
                mpTenantProblemDomainService.addProblemToTenant(tenantId, problemEntity.getId());
        if (!addProblemToTenant.success()) {
            return IrisMessageFactory.fail(addProblemToTenant.message());
        }

        return IrisMessageFactory.success(problemEntity.getId());
    }



    @Override
    public IrisMessage<Long> addTestCaseToProblem(TestCaseDTO testCaseDTO) {

        IrisMessage<TestCaseEntity> testCase = testCaseDomainService.addTestCase(testCaseDTO);
        if (testCase.success()) {
            return IrisMessageFactory.success(testCase.data().getId());
        } else {
            return IrisMessageFactory.fail(testCase.message());
        }

    }
}
