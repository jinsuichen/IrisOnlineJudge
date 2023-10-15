package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.context.UserContext;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.NoSuchUserException;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.dto.ProblemDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.ProblemEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.UserEntity;
import fun.icpc.iris.irisonlinejudge.repo.ProblemRepository;
import fun.icpc.iris.irisonlinejudge.repo.UserRepository;
import fun.icpc.iris.irisonlinejudge.service.domainservice.ProblemDomainService;
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
