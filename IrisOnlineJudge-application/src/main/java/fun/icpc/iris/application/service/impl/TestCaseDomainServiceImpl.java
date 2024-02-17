package fun.icpc.iris.application.service.impl;

import fun.icpc.iris.application.UserContext;
import fun.icpc.iris.application.dto.TestCaseDTO;
import fun.icpc.iris.application.service.domainservice.TestCaseDomainService;
import fun.icpc.iris.domain.entity.table.ProblemEntity;
import fun.icpc.iris.domain.entity.table.TestCaseEntity;
import fun.icpc.iris.domain.entity.table.UserEntity;
import fun.icpc.iris.domain.repository.ProblemRepository;
import fun.icpc.iris.domain.repository.TestCaseRepository;
import fun.icpc.iris.domain.repository.UserRepository;
import fun.icpc.iris.sharedkernel.exception.irisexception.NoSuchProblemException;
import fun.icpc.iris.sharedkernel.exception.irisexception.NoSuchUserException;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
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
