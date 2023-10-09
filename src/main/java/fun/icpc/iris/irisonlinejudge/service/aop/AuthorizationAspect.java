package fun.icpc.iris.irisonlinejudge.service.aop;

import fun.icpc.iris.irisonlinejudge.commons.context.UserContext;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.AuthSystemException;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.NoAuthException;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;
import fun.icpc.iris.irisonlinejudge.domain.enums.GlobalUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.enums.TenantUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.service.domainservice.MpTenantUserDomainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@Aspect
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final MpTenantUserDomainService mpTenantUserDomainService;

    @Before("@annotation(authGlobal)")
    public void checkAuthorization(JoinPoint joinPoint, AuthGlobal authGlobal) {

        GlobalUserRoleTypeEnum requiredRole = authGlobal.value();
        if(Objects.isNull(requiredRole)) {
            throw new AuthSystemException();
        }

        UserDTO userDTO = UserContext.get();
        if(Objects.isNull(userDTO)) {
            // User not login.
            throw new NoAuthException();
        }
        GlobalUserRoleTypeEnum role = userDTO.getRole();


        if (!StringUtils.equals(role.name(), requiredRole.name())) {
            throw new NoAuthException();
        }
    }

    @Before("@annotation(authTenant)")
    public void checkAuthorization(JoinPoint joinPoint, AuthTenant authTenant) {
        TenantUserRoleTypeEnum[] requireAuth = authTenant.value();
        if(ArrayUtils.isEmpty(requireAuth)) {
            throw new AuthSystemException();
        }

        UserDTO userDTO = UserContext.get();
        if(Objects.isNull(userDTO)) {
            // User not login.
            throw new NoAuthException();
        }

        String tenantIdParamName = authTenant.tenantIdParam();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            if (!StringUtils.equals(parameterNames[i], tenantIdParamName)) {
                continue;
            }
            Long tenantId = (Long) args[i];
            IrisMessage<List<TenantUserRoleTypeEnum>> tenantUserRole =
                    mpTenantUserDomainService.getTenantUserRole(userDTO.getId(), tenantId);
            if (!tenantUserRole.success()) {
                throw new NoAuthException();
            }

            boolean ok = Stream.of(requireAuth).allMatch(tenantUserRole.data()::contains);
            if (ok) {
                return;
            }
        }

        throw new AuthSystemException();
    }
}
