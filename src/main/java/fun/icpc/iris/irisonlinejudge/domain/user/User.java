package fun.icpc.iris.irisonlinejudge.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "idx_user_handle", columnList = "handle", unique = true)
})
public class User {

    /**
     * The id of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;

    /**
     * The handle of the user.
     */
    @Column(unique = true, nullable = false, length = 50)
    private String handle;

    /**
     * The nickname of the user.
     */
    @Column(nullable = false, length = 50)
    private String nickname;

    /**
     * The password of the user.
     */
    @Column(nullable = false, length = 200)
    private String password;

    /**
     * The role of the user.
     */
    @Enumerated(EnumType.STRING)
    private Role role;
}
