package fun.icpc.iris.irisonlinejudge.repo;

import fun.icpc.iris.irisonlinejudge.domain.entity.table.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {

}
