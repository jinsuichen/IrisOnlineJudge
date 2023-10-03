package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.context.UserContext;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.AuthSystemException;
import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.NoAuthException;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;
import fun.icpc.iris.irisonlinejudge.domain.enums.GlobalUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.enums.TenantUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.service.UserDomainService;
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

    private final UserDomainService userDomainService;

    @Before("@annotation(authorizationGlobal)")
    public void checkAuthorization(JoinPoint joinPoint, AuthorizationGlobal authorizationGlobal) {

        GlobalUserRoleTypeEnum requiredRole = authorizationGlobal.value();
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

    @Before("@annotation(authorizationTenant)")
    public void checkAuthorization(JoinPoint joinPoint, AuthorizationTenant authorizationTenant) {
        TenantUserRoleTypeEnum[] requireAuth = authorizationTenant.value();
        if(ArrayUtils.isEmpty(requireAuth)) {
            throw new AuthSystemException();
        }

        UserDTO userDTO = UserContext.get();
        if(Objects.isNull(userDTO)) {
            // User not login.
            throw new NoAuthException();
        }

        String tenantIdParamName = authorizationTenant.tenantIdParam();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            if (!StringUtils.equals(parameterNames[i], tenantIdParamName)) {
                continue;
            }
            Long tenantId = (Long) args[i];
            IrisMessage<List<TenantUserRoleTypeEnum>> tenantUserRole =
                    userDomainService.getTenantUserRole(userDTO.getId(), tenantId);
            if (tenantUserRole.isFail()) {
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
