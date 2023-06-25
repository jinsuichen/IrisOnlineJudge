package fun.icpc.iris.irisonlinejudge.user;

import static fun.icpc.iris.irisonlinejudge.user.Permission.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The enum Role.
 */
@RequiredArgsConstructor
public enum Role {

    /**
     * The user role.
     */
    USER(Collections.emptySet()),


    /**
     * The manager role.
     */
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),

    /**
     * The admin role.
     */
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),

    ;

    /**
     * The permissions of the role.
     */
    @Getter
    private final Set<Permission> permissions;

    /**
     * Get the authorities of the role.
     *
     * @return the authorities list of the role.
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}