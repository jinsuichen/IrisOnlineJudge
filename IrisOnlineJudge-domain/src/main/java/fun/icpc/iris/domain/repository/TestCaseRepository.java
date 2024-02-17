package fun.icpc.iris.domain.repository;

import fun.icpc.iris.domain.entity.table.TestCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCaseEntity, Long> {
}
