package fun.icpc.iris.irisonlinejudge.problem;

import jakarta.persistence.*;
import lombok.Data;

/**
 * The test case of a problem.
 */
@Data
@Entity
@Table(name = "tb_test_cases")
public class TestCase {

    /**
     * The id of the test case.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_case_id")
    private Long problemCaseId;

    /**
     * The associated problem.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    /**
     * The input of the test case.
     */
    @Column(name = "input", nullable = false, columnDefinition = "TEXT")
    private String input;

    /**
     * The output of the test case.
     */
    @Column(name = "output", nullable = false, columnDefinition = "TEXT")
    private String output;

    /**
     * The score of the test case.
     */
    @Column(name = "score", nullable = false)
    private Integer score;

    /**
     * Is the test case a sample?
     */
    @Column(name = "is_sample", nullable = false)
    private Boolean isSample;

    /**
     * Is the test case public?
     */
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;
}
