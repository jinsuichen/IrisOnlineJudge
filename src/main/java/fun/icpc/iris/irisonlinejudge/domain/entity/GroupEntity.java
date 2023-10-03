package fun.icpc.iris.irisonlinejudge.domain.entity;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.GroupTeamMapping;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.GroupUserMapping;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_group")
public class GroupEntity {

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
     * The name of the group.
     */
    @Column(nullable = false, length = 200)
    private String name;

    /**
     * The description of the group.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * The associated users.
     */
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<GroupUserMapping> users = new HashSet<>();

    /**
     * The associated tenants.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id", nullable = false)
    private TenantEntity tenant;

    /**
     * The associated teams.
     */
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<GroupTeamMapping> teams = new HashSet<>();

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
