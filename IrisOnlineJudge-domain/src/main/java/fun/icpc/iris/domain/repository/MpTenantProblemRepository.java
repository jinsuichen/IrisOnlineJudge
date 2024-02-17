package fun.icpc.iris.domain.repository;

import fun.icpc.iris.domain.entity.mapping.MpTenantProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MpTenantProblemRepository extends JpaRepository<MpTenantProblem, Long> {

    Optional<MpTenantProblem> findByTenant_IdAndProblem_Id(Long tenantId, Long problemId);
}
