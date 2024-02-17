package fun.icpc.iris.domain.repository;

import fun.icpc.iris.domain.entity.mapping.MpProblemProblemTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MpProblemProblemTagRepository extends JpaRepository<MpProblemProblemTag, Integer> {
}
