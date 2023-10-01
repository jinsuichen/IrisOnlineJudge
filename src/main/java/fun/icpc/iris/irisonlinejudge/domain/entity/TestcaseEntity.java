package fun.icpc.iris.irisonlinejudge.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * The test case of a problem.
 */
@Data
@Entity
@Table(name = "testcase")
public class TestcaseEntity {

    /**
     * The id of the test case.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * The time when the test case was created.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime gmtCreated;

    /**
     * The time when the test case was modified.
     */
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
     * The input of the test case.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String input;

    /**
     * The output of the test case.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String output;

    /**
     * The score of the test case.
     */
    @Column(nullable = false)
    private Integer score;

    /**
     * Is the test case a sample?
     */
    @Column(nullable = false)
    private Boolean isSample;

    /**
     * Is the test case public?
     */
    @Column(nullable = false)
    private Boolean isPublic;

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
