package fun.icpc.iris.irisonlinejudge.domain.entity.table;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpProblemProblemTag;
import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpTenantProblem;
import fun.icpc.iris.irisonlinejudge.domain.enums.CheckerTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.enums.ProblemDifficultyTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The problem.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_Problem")
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
    private List<TestCaseEntity> testCases;

    /**
     * The associated judge type.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CheckerTypeEnum checkerType;

    /**
     * The associated tenants.
     */
    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private List<MpTenantProblem> tenants;

    /**
     * The associated problem tags
     */
    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private List<MpProblemProblemTag> tags;

    /**
     * The associated problem difficulty
     */
    @Column()
    @Enumerated(EnumType.STRING)
    private ProblemDifficultyTypeEnum difficulty;

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
