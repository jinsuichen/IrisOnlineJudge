package fun.icpc.iris.irisonlinejudge.service.domainservice;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.dto.ProblemDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.table.ProblemEntity;

public interface ProblemDomainService {

    /**
     * Add a problem with basic info
     *
     * @param problemDTO problemDTO
     * @return ProblemEntity
     */
    IrisMessage<ProblemEntity> addProblemWithBasicInfo(ProblemDTO problemDTO);

}
