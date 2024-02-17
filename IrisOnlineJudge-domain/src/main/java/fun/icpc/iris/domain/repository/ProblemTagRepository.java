package fun.icpc.iris.domain.repository;

import fun.icpc.iris.domain.entity.table.ProblemTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProblemTagRepository extends JpaRepository<ProblemTagEntity, Long> {

    Optional<ProblemTagEntity> findByName(String name);
}
