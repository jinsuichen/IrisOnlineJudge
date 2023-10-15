package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.context.UserContext;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.NoSuchProblemException;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.NoSuchUserException;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.dto.TestCaseDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.ProblemEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.TestCaseEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.UserEntity;
import fun.icpc.iris.irisonlinejudge.repo.ProblemRepository;
import fun.icpc.iris.irisonlinejudge.repo.TestCaseRepository;
import fun.icpc.iris.irisonlinejudge.repo.UserRepository;
import fun.icpc.iris.irisonlinejudge.service.domainservice.TestCaseDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestCaseDomainServiceImpl implements TestCaseDomainService {

    private final TestCaseRepository testCaseRepository;

    private final UserRepository userRepository;

    private final ProblemRepository problemRepository;

    @Override
    public IrisMessage<TestCaseEntity> addTestCase(TestCaseDTO testCaseDTO) {

        Long userId = UserContext.get().getId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(NoSuchUserException::new);

        ProblemEntity problemEntity = problemRepository.findById(testCaseDTO.getProblemId())
                .orElseThrow(NoSuchProblemException::new);

        TestCaseEntity testCaseEntity = TestCaseEntity.builder()
                .input(testCaseDTO.getInput())
                .output(testCaseDTO.getOutput())
                .isPublic(testCaseDTO.getIsPublic())
                .isSample(testCaseDTO.getIsSample())
                .score(testCaseDTO.getScore())
                .creator(userEntity)
                .problem(problemEntity)
                .build();

        testCaseEntity = testCaseRepository.save(testCaseEntity);

        return IrisMessageFactory.success(testCaseEntity);
    }
}
