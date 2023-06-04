package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.permission.Permission;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * The user.
 */
@Data
@Entity
@Table(name = "tb_users")
@EqualsAndHashCode(exclude = "permissions")
public class User {

    /**
     * The id of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * The handle of the user.
     */
    @Column(name = "handle", unique = true, nullable = false, length = 50)
    private String handle;

    /**
     * The nickname of the user.
     */
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    /**
     * The password of the user.
     */
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    /**
     * Associated permissions.
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_users_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

}
