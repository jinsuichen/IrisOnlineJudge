package fun.icpc.iris.application.service.aop;

import fun.icpc.iris.sharedkernel.enums.TenantUserRoleTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthTenant {

    TenantUserRoleTypeEnum[] value() default {} ;

    String tenantIdParam() default "tenantId";
}
