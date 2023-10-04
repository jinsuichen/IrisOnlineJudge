package fun.icpc.iris.irisonlinejudge.service;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;

public interface TenantService {

    /**
     * Create a tenant with tenant purchase plan.
     *
     */
    IrisMessage<Integer> createTenant();

}
