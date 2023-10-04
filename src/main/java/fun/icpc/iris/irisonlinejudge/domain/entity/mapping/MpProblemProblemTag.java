package fun.icpc.iris.irisonlinejudge.domain.entity.mapping;

import fun.icpc.iris.irisonlinejudge.domain.entity.ProblemEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.ProblemTagEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mp_problem_problemtag")
public class MpProblemProblemTag {

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
     * The associated problemTag.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "problemtag_id", nullable = false)
    private ProblemTagEntity problemTag;

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
