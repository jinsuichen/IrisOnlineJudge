package fun.icpc.iris.domain.repository;

import fun.icpc.iris.domain.entity.table.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<TenantEntity, Long> {


    boolean existsByName(String name);

}
