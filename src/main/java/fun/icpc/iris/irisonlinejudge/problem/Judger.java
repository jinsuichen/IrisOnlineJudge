package fun.icpc.iris.irisonlinejudge.problem;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * The Dockerfile of a problem.
 */
@Data
@Entity
public class Judger {

    /**
     * The id of the Dockerfile.
     */
    @Id
    @Column
    private Long judgerId;

    /**
     * The name of the Dockerfile.
     */
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private JudgerTypeEnum judgerType;

    /**
     * The description of the Dockerfile.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * Is the Dockerfile enabled?
     */
    @Column(nullable = false)
    private Boolean isEnabled;

    /**
     * The associated problems.
     */
    @OneToMany(mappedBy = "judger", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Problem> problems;
}
