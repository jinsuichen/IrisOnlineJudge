package fun.icpc.iris.irisonlinejudge.controller;

import fun.icpc.iris.irisonlinejudge.commons.util.ConstraintValidator;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.record.CreateTenantRequest;
import fun.icpc.iris.irisonlinejudge.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenant")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping("/")
    public IrisMessage<Long> createTenant(@RequestBody CreateTenantRequest createTenantRequest) {
        IrisMessage<Boolean> tenantName =
                ConstraintValidator.validateTenantName(createTenantRequest.tenantDTO().getName());
        if (tenantName.isFail()) {
            return IrisMessageFactory.fail(tenantName.message());
        }

        return tenantService.createTenant(
                createTenantRequest.tenantDTO(),
                createTenantRequest.purchasePlanId());
    }

}
