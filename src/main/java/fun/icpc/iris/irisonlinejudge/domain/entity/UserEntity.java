package fun.icpc.iris.irisonlinejudge.domain.entity;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpGroupUser;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpTeamUser;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpTenantUser;
import fun.icpc.iris.irisonlinejudge.domain.enums.CountryAndRegionTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.enums.GenderTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.enums.GlobalUserRoleTypeEnum;
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
@Table(name = "tb_user")
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
    private GlobalUserRoleTypeEnum role;

    /**
     * The associated tenants
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<MpTenantUser> mpTenantUsers;

    /**
     * The associated teams.
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<MpTeamUser> mpTeamUsers;

    /**
     * The associated groups.
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<MpGroupUser> mpGroupUsers;

    /**
     * unfreeze time
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime unfreezeTime;

    /**
     * The email of the user.
     */
    @Column(length = 200)
    private String email;

    /**
     * The bio of the user.
     */
    @Column(columnDefinition = "TEXT")
    private String bio;

    /**
     * The avatar of the user.
     */
    @Column(columnDefinition = "TEXT")
    private String avatar;

    /**
     * The gender of the user.
     */
    @Column(length = 80)
    private GenderTypeEnum gender;

    /**
     * The birthday of the user.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private LocalDateTime birthday;

    /**
     * The country and region of the user.
     */
    @Column(columnDefinition = "TEXT")
    private CountryAndRegionTypeEnum countryAndRegion;

    /**
     * The organization of the user.
     */
    @Column(length = 200)
    private String organization;

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
