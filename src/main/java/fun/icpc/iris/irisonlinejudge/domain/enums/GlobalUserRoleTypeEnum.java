package fun.icpc.iris.irisonlinejudge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The users' role in the global system.
 * User has only one role in the global system.
 */
@Getter
@AllArgsConstructor
public enum GlobalUserRoleTypeEnum {
    USER("User"),
    ADMIN("Admin"),
    SYS_ADMIN("System Admin"),
    BOT("Bot"),
    SYSTEM("System")
    ;

    private final String display;
}
