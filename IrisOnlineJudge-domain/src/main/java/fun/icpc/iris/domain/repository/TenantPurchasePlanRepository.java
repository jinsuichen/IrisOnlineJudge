package fun.icpc.iris.domain.repository;

import fun.icpc.iris.domain.entity.table.TenantPurchasePlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantPurchasePlanRepository extends JpaRepository<TenantPurchasePlanEntity, Long> {
}
