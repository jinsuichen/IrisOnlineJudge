package fun.icpc.iris.irisonlinejudge.domain.problem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The test case of a problem.
 */
@Data
@Entity
@Table(name = "testcase")
public class TestCase {

    /**
     * The id of the test case.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long problemCaseId;

    /**
     * The associated problem.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

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
}
