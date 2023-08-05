package fun.icpc.iris.irisonlinejudge.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("User"),
    MANAGER("Manager"),
    ADMIN("Admin");

    private final String roleName;
}