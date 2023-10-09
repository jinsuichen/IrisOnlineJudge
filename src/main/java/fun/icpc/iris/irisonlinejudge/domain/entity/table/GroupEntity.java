package fun.icpc.iris.irisonlinejudge.domain.entity.table;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpGroupTeam;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpGroupUser;
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
@Table(name = "tb_Group")
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
    private Set<MpGroupUser> users = new HashSet<>();

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
    private Set<MpGroupTeam> teams = new HashSet<>();

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
