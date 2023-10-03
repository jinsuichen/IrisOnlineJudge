package fun.icpc.iris.irisonlinejudge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The users' role in the global system.
 */
@Getter
@AllArgsConstructor
public enum UserRoleTypeEnum {
    USER,
    ADMIN,
    SYS_ADMIN,
    BOT,
    SYSTEM,
}
