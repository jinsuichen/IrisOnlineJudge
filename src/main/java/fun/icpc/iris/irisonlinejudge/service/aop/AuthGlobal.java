package fun.icpc.iris.irisonlinejudge.service.aop;

import fun.icpc.iris.irisonlinejudge.domain.enums.GlobalUserRoleTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthGlobal {

    GlobalUserRoleTypeEnum value();

}
