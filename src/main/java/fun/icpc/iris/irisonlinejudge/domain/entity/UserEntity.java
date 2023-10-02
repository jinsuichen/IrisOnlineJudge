package fun.icpc.iris.irisonlinejudge.domain.entity;

import fun.icpc.iris.irisonlinejudge.domain.enums.RoleTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", indexes = {@Index(name = "idx_user_handle", columnList = "handle", unique = true)})
public class UserEntity {

    /**
     * The id of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * The time when the user was created.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime gmtCreated;

    /**
     * The time when the user was modified.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime gmtModified;

    /**
     * The handle of the user.
     */
    @Column(unique = true, nullable = false, length = 200)
    private String handle;

    /**
     * The nickname of the user.
     */
    @Column(nullable = false, length = 200)
    private String nickName;

    /**
     * The password of the user.
     */
    @Column(nullable = false, length = 200)
    private String password;

    /**
     * The role of the user.
     */
    @Enumerated(EnumType.STRING)
    private RoleTypeEnum role;

    /**
     * unfreeze time
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime unfreezeTime;

    /**
     * When the problem is created, set the created time and the last updated time to the current time.
     */
    @PrePersist
    protected void onCreate() {
        this.gmtCreated = LocalDateTime.now();
        this.gmtModified = LocalDateTime.now();
        this.unfreezeTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
    }

    /**
     * When the problem is created, set the last updated time to the current time.
     */
    @PreUpdate
    protected void onUpdate() {
        this.gmtModified = LocalDateTime.now();
    }
}
