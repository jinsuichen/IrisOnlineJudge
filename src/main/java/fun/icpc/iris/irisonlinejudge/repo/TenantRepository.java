package fun.icpc.iris.irisonlinejudge.repo;

import fun.icpc.iris.irisonlinejudge.domain.entity.table.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<TenantEntity, Long> {


    boolean existsByName(String name);

}
