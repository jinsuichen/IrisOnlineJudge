package fun.icpc.iris.irisonlinejudge.datainit;

import fun.icpc.iris.irisonlinejudge.domain.entity.TenantPurchasePlanEntity;
import fun.icpc.iris.irisonlinejudge.repo.TenantPurchasePlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TenantPurchasePlanInitializer implements CommandLineRunner {

    private final TenantPurchasePlanRepository tenantPurchasePlanRepository;

    @Override
    public void run(String... args) throws Exception {

        if(tenantPurchasePlanRepository.count() > 0) {
            return;
        }

        TenantPurchasePlanEntity freePlan = TenantPurchasePlanEntity.builder()
                .name("Free Plan")
                .description("")
                .priceBeforeDiscount(0L)
                .priceAfterDiscount(0L)
                .memberLimit(50L)
                .commitLimit(50 * 1000L)
                .duration(Duration.ofDays(30))
                .build();

        TenantPurchasePlanEntity corePlan = TenantPurchasePlanEntity.builder()
                .name("Core Plan")
                .description("")
                .priceBeforeDiscount(800L) // 8.00 USD
                .priceAfterDiscount(600L) // 6.00 USD
                .memberLimit(300L)
                .commitLimit(300 * 1000L)
                .duration(Duration.ofDays(30))
                .build();

        TenantPurchasePlanEntity proPlan = TenantPurchasePlanEntity.builder()
                .name("Pro Plan")
                .description("")
                .priceBeforeDiscount(1600L) // 16.00 USD
                .priceAfterDiscount(1200L) // 12.00 USD
                .memberLimit(1000L)
                .commitLimit(1000 * 1000L)
                .duration(Duration.ofDays(30))
                .build();

        TenantPurchasePlanEntity ultraPlan = TenantPurchasePlanEntity.builder()
                .name("Ultra Plan")
                .description("")
                .priceBeforeDiscount(3200L) // 32.00 USD
                .priceAfterDiscount(2400L) // 24.00 USD
                .memberLimit(5000L)
                .commitLimit(5000 * 1000L)
                .duration(Duration.ofDays(30))
                .build();

        TenantPurchasePlanEntity unlimitedPlan = TenantPurchasePlanEntity.builder()
                .name("Unlimited Plan")
                .description("")
                .priceBeforeDiscount(6800L) // 68.00 USD
                .priceAfterDiscount(4800L) // 48.00 USD
                .memberLimit(-1L)
                .commitLimit(-1L)
                .duration(Duration.ofDays(30))
                .build();

        tenantPurchasePlanRepository.saveAll(List.of(
                freePlan, corePlan, proPlan, ultraPlan, unlimitedPlan));
    }
}
