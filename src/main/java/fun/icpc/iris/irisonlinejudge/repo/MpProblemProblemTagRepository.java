package fun.icpc.iris.irisonlinejudge.repo;

import fun.icpc.iris.irisonlinejudge.domain.entity.mapping.MpProblemProblemTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MpProblemProblemTagRepository extends JpaRepository<MpProblemProblemTag, Integer> {
}
