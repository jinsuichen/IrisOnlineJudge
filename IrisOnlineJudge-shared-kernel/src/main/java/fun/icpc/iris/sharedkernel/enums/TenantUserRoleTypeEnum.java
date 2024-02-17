package fun.icpc.iris.sharedkernel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The users' role in the tenant.
 * User may have <b>multiple roles</b> in the tenant.
 */
@Getter
@AllArgsConstructor
public enum TenantUserRoleTypeEnum {
    CONTESTANT("Contestant"),
    PROBLEM_AUTHOR("Problem Author"),
    ANALYST("Analyst"),
    MANAGER("Manager"),
    OWNER("Owner")
    ;

    private final String display;
}