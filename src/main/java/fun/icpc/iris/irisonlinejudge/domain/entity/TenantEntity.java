package fun.icpc.iris.irisonlinejudge.domain.entity;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.TenantProblemMapping;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.TenantUserMapping;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_tenant")
public class TenantEntity {

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
     * The name of the tenant.
     */
    @Column(nullable = false, length = 200)
    private String name;

    /**
     * The description of the tenant.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * The member limit of the tenant.
     */
    @Column(nullable = false)
    private Long memberLimit;

    /**
     * The commit limit of the tenant.
     */
    @Column(nullable = false)
    private Long commitLimit;

    /**
     * The associated users.
     */
    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
    private Set<TenantUserMapping> users = new HashSet<>();

    /**
     * The associated groups.
     */
    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
    private Set<GroupEntity> groups = new HashSet<>();

    /**
     * The associated problems.
     */
    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
    private Set<TenantProblemMapping> problems = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.gmtCreated = LocalDateTime.now();
        this.gmtModified = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.gmtModified = LocalDateTime.now();
    }

}
