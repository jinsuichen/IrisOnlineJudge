package fun.icpc.iris.irisonlinejudge.problem;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tb_problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private Long problemId;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "time_limit", nullable = false)
    private Long timeLimit;

    @Column(name = "memory_limit", nullable = false)
    private Long memoryLimit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_time", nullable = false)
    private Date lastUpdatedTime;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestCase> testCases;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "judger_id", nullable = false)
    private Judger judger;

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedTime = new Date();
    }


}
