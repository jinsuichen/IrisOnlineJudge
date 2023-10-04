package fun.icpc.iris.irisonlinejudge.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The test case of a problem.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_testcase")
public class TestCaseEntity {

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
