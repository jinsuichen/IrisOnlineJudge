package fun.icpc.iris.irisonlinejudge.domain.entity;

import fun.icpc.iris.irisonlinejudge.domain.enums.JudgerTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
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
    private Long problemId;

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
     * The last updated time of the problem.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastUpdatedTime;

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
     * When the problem is created, set the last updated time to the current time.
     */
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedTime = new Date();
    }


}
