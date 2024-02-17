package fun.icpc.iris.adapter.rest.controller;

import fun.icpc.iris.application.ConstraintValidator;
import fun.icpc.iris.application.command.CreateTenantCommand;
import fun.icpc.iris.application.service.domainservice.TenantDomainService;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenant")
@RequiredArgsConstructor
public class TenantController {

    private final TenantDomainService tenantDomainService;

    @PostMapping("/")
    public IrisMessage<Long> createTenant(@RequestBody CreateTenantCommand createTenantCommand) {
        IrisMessage<Boolean> tenantName =
                ConstraintValidator.validateTenantName(createTenantCommand.tenantDTO().getName());
        if (!tenantName.success()) {
            return IrisMessageFactory.fail(tenantName.message());
        }

        return tenantDomainService.createTenant(
                createTenantCommand.tenantDTO(),
                createTenantCommand.purchasePlanId());
    }

}