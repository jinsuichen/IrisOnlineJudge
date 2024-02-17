package fun.icpc.iris.application.service.domainservice;


import fun.icpc.iris.domain.entity.mapping.MpTenantProblem;
import fun.icpc.iris.sharedkernel.util.IrisMessage;

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
