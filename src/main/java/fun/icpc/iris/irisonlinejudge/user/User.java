package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.permission.Permission;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_users")
@EqualsAndHashCode(exclude = "permissions")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "handle", unique = true, nullable = false, length = 50)
    private String handle;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_users_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

}
