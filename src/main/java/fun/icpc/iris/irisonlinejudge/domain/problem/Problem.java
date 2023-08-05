package fun.icpc.iris.irisonlinejudge.domain.problem;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * The problem.
 */
@Data
@Entity
public class Problem {

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
    private List<TestCase> testCases;

    /**
     * The associated Dockerfile.
     */
    @ManyToOne(fetch = FetchType.EAGER)
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
