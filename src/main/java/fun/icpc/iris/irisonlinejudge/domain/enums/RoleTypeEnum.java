package fun.icpc.iris.irisonlinejudge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleTypeEnum {
    CONTESTANT("Contestant"),
    MANAGER("Manager"),
    COACH("Coach"),
    ADMIN("Admin"),
    SYSTEM("System"),
    ;

    private final String roleName;
}