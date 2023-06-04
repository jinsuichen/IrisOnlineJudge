package fun.icpc.iris.irisonlinejudge.permission;

import fun.icpc.iris.irisonlinejudge.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * The permission of a user.
 */
@Data
@Entity
@Table(name = "tb_permissions")
@EqualsAndHashCode(exclude = "users")
public class Permission {

    /**
     * The id of the permission.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    /**
     * The name of the permission.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The associated users.
     */
    @ManyToMany(mappedBy = "permissions")
    private Set<User> users = new HashSet<>();

}
