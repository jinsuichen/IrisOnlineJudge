package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.permission.Permission;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String handle;

    private String nickname;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions = new ArrayList<>();

}
