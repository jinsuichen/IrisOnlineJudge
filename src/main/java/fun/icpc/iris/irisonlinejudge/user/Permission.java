package fun.icpc.iris.irisonlinejudge.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The enum Permission.
 */
@RequiredArgsConstructor
public enum Permission {

    /**
     * Admin read permissions.
     */
    ADMIN_READ("admin:read"),

    /**
     * Admin update permissions.
     */
    ADMIN_UPDATE("admin:update"),

    /**
     * Admin create permissions.
     */
    ADMIN_CREATE("admin:create"),

    /**
     * Admin delete permissions.
     */
    ADMIN_DELETE("admin:delete"),

    /**
     * Manager read permissions.
     */
    MANAGER_READ("management:read"),

    /**
     * Manager update permissions.
     */
    MANAGER_UPDATE("management:update"),

    /**
     * Manager create permissions.
     */
    MANAGER_CREATE("management:create"),

    /**
     * Manager delete permissions.
     */
    MANAGER_DELETE("management:delete");

    @Getter
    private final String permission;
}