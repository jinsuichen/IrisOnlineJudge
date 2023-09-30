package fun.icpc.iris.irisonlinejudge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleTypeEnum {
    USER("User"),
    MANAGER("Manager"),
    ADMIN("Admin");

    private final String roleName;
}