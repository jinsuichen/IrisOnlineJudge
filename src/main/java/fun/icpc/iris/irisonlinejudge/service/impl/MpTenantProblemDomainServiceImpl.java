package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.NoSuchProblemException;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.NoSuchTenantException;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpTenantProblem;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.ProblemEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.TenantEntity;
import fun.icpc.iris.irisonlinejudge.repo.MpTenantProblemRepository;
import fun.icpc.iris.irisonlinejudge.repo.ProblemRepository;
import fun.icpc.iris.irisonlinejudge.repo.TenantRepository;
import fun.icpc.iris.irisonlinejudge.service.domainservice.MpTenantProblemDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MpTenantProblemDomainServiceImpl implements MpTenantProblemDomainService {

    private final MpTenantProblemRepository mpTenantProblemRepository;

    private final TenantRepository tenantRepository;

    private final ProblemRepository problemRepository;

    @Override
    public IrisMessage<MpTenantProblem> addProblemToTenant(Long tenantId, Long problemId) {
        TenantEntity tenantEntity = tenantRepository.findById(tenantId)
                .orElseThrow(NoSuchTenantException::new);

        ProblemEntity problemEntity = problemRepository.findById(problemId)
                .orElseThrow(NoSuchProblemException::new);

        Optional<MpTenantProblem> mpTenantProblemOptional =
                mpTenantProblemRepository.findByTenant_IdAndProblem_Id(tenantId, problemId);

        if (mpTenantProblemOptional.isPresent()) {
            log.info("Problem {} already in tenant {}", problemId, tenantId);
            return IrisMessageFactory.success(mpTenantProblemOptional.get());
        }

        MpTenantProblem ret = mpTenantProblemRepository.save(
                MpTenantProblem.builder()
                        .tenant(tenantEntity)
                        .problem(problemEntity)
                        .build());
        return IrisMessageFactory.success(ret);

    }
}
