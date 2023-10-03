package fun.icpc.iris.irisonlinejudge.domain.entity;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpTenantProblem;
import fun.icpc.iris.irisonlinejudge.domain.enums.JudgerTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The problem.
 */
@Data
@Entity
@Table(name = "tb_problem")
public class ProblemEntity {

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
     * The title of the problem.
     */
    @Column(length = 200, nullable = false)
    private String title;

    /**
     * The content of the problem.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * The time limit of the problem.
     */
    @Column(nullable = false)
    private Long timeLimit;

    /**
     * The memory limit of the problem.
     */
    @Column(nullable = false)
    private Long memoryLimit;

    /**
     * The associated test cases.
     */
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestcaseEntity> testcases;

    /**
     * The associated judge type.
     */
    @Column(nullable = false)
    private JudgerTypeEnum judgeType;

    /**
     * The associated tenants.
     */
    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private List<MpTenantProblem> tenants;

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
