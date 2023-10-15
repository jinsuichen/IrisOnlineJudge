package fun.icpc.iris.irisonlinejudge.service.domainservice;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpTenantProblem;

public interface MpTenantProblemDomainService {

    /**
     * Add problem to tenant
     *
     * @param tenantId  tenantId
     * @param problemId problemId
     * @return is success
     */
    IrisMessage<MpTenantProblem> addProblemToTenant(Long tenantId, Long problemId);

}
