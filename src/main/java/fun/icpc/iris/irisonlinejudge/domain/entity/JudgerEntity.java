package fun.icpc.iris.irisonlinejudge.domain.entity;

import fun.icpc.iris.irisonlinejudge.domain.enums.JudgerTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * The Dockerfile of a problem.
 */
@Data
@Entity
@Table(name = "judger")
public class JudgerEntity {

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
    private List<ProblemEntity> problems;
}
