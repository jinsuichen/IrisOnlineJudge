package fun.icpc.iris.irisonlinejudge.repo;

import fun.icpc.iris.irisonlinejudge.domain.entity.table.ProblemTagCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProblemTagCatalogRepository extends JpaRepository<ProblemTagCatalogEntity, Long> {

    Optional<ProblemTagCatalogEntity> findByName(String name);
}
