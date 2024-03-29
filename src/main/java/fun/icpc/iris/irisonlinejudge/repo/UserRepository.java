package fun.icpc.iris.irisonlinejudge.repo;

import fun.icpc.iris.irisonlinejudge.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The user repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByHandle(String handle);

    boolean existsByHandle(String handle);

}
