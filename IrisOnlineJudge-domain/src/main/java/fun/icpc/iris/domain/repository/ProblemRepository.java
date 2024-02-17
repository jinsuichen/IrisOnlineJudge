package fun.icpc.iris.domain.repository;

import fun.icpc.iris.domain.entity.table.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {

}
