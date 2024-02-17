package fun.icpc.iris.application.service.impl;

import fun.icpc.iris.application.UserContext;
import fun.icpc.iris.application.dto.ProblemDTO;
import fun.icpc.iris.application.service.domainservice.ProblemDomainService;
import fun.icpc.iris.domain.entity.table.ProblemEntity;
import fun.icpc.iris.domain.entity.table.UserEntity;
import fun.icpc.iris.domain.repository.ProblemRepository;
import fun.icpc.iris.domain.repository.UserRepository;
import fun.icpc.iris.sharedkernel.exception.irisexception.NoSuchUserException;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProblemDomainServiceImpl implements ProblemDomainService {

    private final ProblemRepository problemRepository;

    private final UserRepository userRepository;

    @Override
    public IrisMessage<ProblemEntity> addProblemWithBasicInfo(ProblemDTO problemDTO) {

        Long userId = UserContext.get().getId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(NoSuchUserException::new);

        ProblemEntity problemEntity = ProblemEntity.builder()
                .title(problemDTO.getTitle())
                .content(problemDTO.getContent())
                .timeLimit(problemDTO.getTimeLimit())
                .memoryLimit(problemDTO.getMemoryLimit())
                .checkerType(problemDTO.getCheckerType())
                .difficulty(problemDTO.getDifficulty())
                .isPublic(problemDTO.getIsPublic())
                .creator(userEntity)
                .build();


        problemEntity = problemRepository.save(problemEntity);

        return IrisMessageFactory.success(problemEntity);
    }
}
