package fun.icpc.iris.irisonlinejudge.domain.entity;

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
@Table(name = "problem")
public class ProblemEntity {

    /**
     * The id of the problem.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * The time when the problem was created.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime gmtCreated;

    /**
     * The time when the problem was modified.
     */
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
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TestcaseEntity> testcases;

    /**
     * The associated judge type.
     */
    @Column(nullable = false)
    private JudgerTypeEnum judgeType;

    /**
     * When the problem is created, set the created time and the last updated time to the current time.
     */
    @PrePersist
    protected void onCreate() {
        this.gmtCreated = LocalDateTime.now();
        this.gmtModified = LocalDateTime.now();
    }

    /**
     * When the problem is created, set the last updated time to the current time.
     */
    @PreUpdate
    protected void onUpdate() {
        this.gmtModified = LocalDateTime.now();
    }
}
