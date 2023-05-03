package fun.icpc.iris.irisonlinejudge.problem;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tb_judgers")
public class Judger {

    @Id
    @Column(name = "judger_id")
    private Long judgerId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "judger_id")
    private List<Problem> problems;
}
