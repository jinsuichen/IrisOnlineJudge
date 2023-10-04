package fun.icpc.iris.irisonlinejudge.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_TenantPurchasePlan")
public class TenantPurchasePlanEntity {

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

    @Column(nullable = false, length = 200)
    private String name;

    /**
     * The description of the tenant purchase plan.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * The member limit of the tenant purchase plan.
     */
    @Column(nullable = false)
    private Long memberLimit;

    /**
     * The commit limit of the tenant purchase plan.
     */
    @Column(nullable = false)
    private Long commitLimit;

    /**
     * The price before discount of the tenant purchase plan.
     */
    @Column(nullable = false)
    private Long priceBeforeDiscount;

    /**
     * The price after discount of the tenant purchase plan.
     */
    @Column(nullable = false)
    private Long priceAfterDiscount;

    /**
     * The duration of the tenant purchase plan.
     */
    @Column(nullable = false)
    private Duration duration;

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
