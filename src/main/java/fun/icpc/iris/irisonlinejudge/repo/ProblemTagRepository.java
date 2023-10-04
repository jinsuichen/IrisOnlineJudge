package fun.icpc.iris.irisonlinejudge.repo;

import fun.icpc.iris.irisonlinejudge.domain.entity.ProblemTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemTagRepository extends JpaRepository<ProblemTagEntity, Long> {

    Optional<ProblemTagEntity> findByName(String name);
}
