package fun.icpc.iris.irisonlinejudge.repo;

import java.util.Optional;

import fun.icpc.iris.irisonlinejudge.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The user repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByHandle(String handle);

    boolean existsByHandle(String handle);

}
