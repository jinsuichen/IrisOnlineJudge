package fun.icpc.iris.irisonlinejudge.domain.entity.mapping;

import fun.icpc.iris.irisonlinejudge.domain.entity.ProblemEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.TenantEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mp_tenant_problem_mapping")
public class TenantProblemMapping {

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
     * The associated problem.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id", nullable = false)
    private ProblemEntity problem;

    /**
     * The associated tenant.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id", nullable = false)
    private TenantEntity tenant;

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
