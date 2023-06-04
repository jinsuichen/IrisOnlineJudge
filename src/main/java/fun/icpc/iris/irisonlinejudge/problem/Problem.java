package fun.icpc.iris.irisonlinejudge.problem;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * The problem.
 */
@Data
@Entity
@Table(name = "tb_problems")
public class Problem {

    /**
     * The id of the problem.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private Long problemId;

    /**
     * The title of the problem.
     */
    @Column(name = "title", length = 200, nullable = false)
    private String title;

    /**
     * The content of the problem.
     */
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * The time limit of the problem.
     */
    @Column(name = "time_limit", nullable = false)
    private Long timeLimit;

    /**
     * The memory limit of the problem.
     */
    @Column(name = "memory_limit", nullable = false)
    private Long memoryLimit;

    /**
     * The last updated time of the problem.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_time", nullable = false)
    private Date lastUpdatedTime;

    /**
     * The associated test cases.
     */
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestCase> testCases;

    /**
     * The associated judger.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "judger_id", nullable = false)
    private Judger judger;

    /**
     * When the problem is created, set the last updated time to the current time.
     */
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedTime = new Date();
    }


}
