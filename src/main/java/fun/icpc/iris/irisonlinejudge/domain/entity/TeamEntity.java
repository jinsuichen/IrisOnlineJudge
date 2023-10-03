package fun.icpc.iris.irisonlinejudge.domain.entity;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.GroupTeamMapping;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.TeamUserMapping;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_team")
public class TeamEntity {

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
     * The name of the team.
     */
    @Column(nullable = false, length = 200)
    private String name;

    /**
     * The description of the team.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * The associated users.
     */
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TeamUserMapping> users = new HashSet<>();

    /**
     * The associated groups.
     */
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GroupTeamMapping> groups = new HashSet<>();

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
