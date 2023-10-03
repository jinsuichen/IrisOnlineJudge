package fun.icpc.iris.irisonlinejudge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The users' role in the tenant.
 */
@Getter
@AllArgsConstructor
public enum TenantAuthTypeEnum {
    CONTESTANT("Contestant"),
    PROBLEM_AUTHOR("Problem Author"),
    ANALYST("Analyst"),
    OWNER("Owner")
    ;

    private final String display;
}