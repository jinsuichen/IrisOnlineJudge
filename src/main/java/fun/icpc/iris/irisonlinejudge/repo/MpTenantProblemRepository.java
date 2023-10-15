package fun.icpc.iris.irisonlinejudge.repo;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpTenantProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MpTenantProblemRepository extends JpaRepository<MpTenantProblem, Long> {

    Optional<MpTenantProblem> findByTenant_IdAndProblem_Id(Long tenantId, Long problemId);
}
