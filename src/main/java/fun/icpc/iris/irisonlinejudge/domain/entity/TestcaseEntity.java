package fun.icpc.iris.irisonlinejudge.problem;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_test_cases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_case_id")
    private Long problemCaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(name = "input", nullable = false, columnDefinition = "TEXT")
    private String input;

    @Column(name = "output", nullable = false, columnDefinition = "TEXT")
    private String output;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "is_sample", nullable = false)
    private Boolean isSample;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;
}
