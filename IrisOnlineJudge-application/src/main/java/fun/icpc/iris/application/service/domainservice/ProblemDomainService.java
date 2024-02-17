package fun.icpc.iris.application.service.domainservice;

import fun.icpc.iris.application.dto.ProblemDTO;
import fun.icpc.iris.domain.entity.table.ProblemEntity;
import fun.icpc.iris.sharedkernel.util.IrisMessage;

public interface ProblemDomainService {

    /**
     * Add a problem with basic info
     *
     * @param problemDTO problemDTO
     * @return ProblemEntity
     */
    IrisMessage<ProblemEntity> addProblemWithBasicInfo(ProblemDTO problemDTO);

}
