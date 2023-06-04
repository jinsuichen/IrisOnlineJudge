package fun.icpc.iris.irisonlinejudge.problem;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * The judger of a problem.
 */
@Data
@Entity
@Table(name = "tb_judgers")
public class Judger {

    /**
     * The id of the judger.
     */
    @Id
    @Column(name = "judger_id")
    private Long judgerId;

    /**
     * The name of the judger.
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * The description of the judger.
     */
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * Is the judger enabled?
     */
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    /**
     * The associated problems.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "judger_id")
    private List<Problem> problems;
}
