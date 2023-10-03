package fun.icpc.iris.irisonlinejudge.domain.entity.mapping;

import fun.icpc.iris.irisonlinejudge.domain.entity.GroupEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mp_group_user")
public class GroupUserMapping {

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
     * The associated group.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;

    /**
     * The associated user.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

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
