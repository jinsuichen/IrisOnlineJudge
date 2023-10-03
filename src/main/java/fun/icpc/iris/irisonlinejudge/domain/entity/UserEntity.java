package fun.icpc.iris.irisonlinejudge.domain.entity;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.GroupUserMapping;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.TeamUserMapping;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.TenantUserMapping;
import fun.icpc.iris.irisonlinejudge.domain.enums.UserRoleTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * The user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user", indexes = {@Index(name = "idx_user_handle", columnList = "handle", unique = true)})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime gmtCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime gmtModified;

    /**
     * The handle of the user.
     */
    @Column(unique = true, nullable = false, length = 200)
    private String handle;

    /**
     * The nickname of the user.
     */
    @Column(nullable = false, length = 200)
    private String nickName;

    /**
     * The password of the user.
     */
    @Column(nullable = false, length = 200)
    private String password;

    /**
     * The role of the user in global scope.
     */
    @Enumerated(EnumType.STRING)
    private UserRoleTypeEnum role;

    /**
     * The associated tenants
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<TenantUserMapping> tenantUserMappings;

    /**
     * The associated teams.
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<TeamUserMapping> teamUserMappings;

    /**
     * The associated groups.
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<GroupUserMapping> groupUserMappings;

    /**
     * unfreeze time
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime unfreezeTime;

    @PrePersist
    protected void onCreate() {
        this.gmtCreated = LocalDateTime.now();
        this.gmtModified = LocalDateTime.now();
        this.unfreezeTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
    }

    @PreUpdate
    protected void onUpdate() {
        this.gmtModified = LocalDateTime.now();
    }
}
