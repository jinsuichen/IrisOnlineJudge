package fun.icpc.iris.irisonlinejudge.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindById() {
        // Insert a user into the database
        User user = new User();
        user.setNickname("nick");
        user.setHandle("handle");
        entityManager.persist(user);

        // Query the user from the database
        Optional<User> result = userRepository.findById(user.getUserId());
        assertTrue(result.isPresent());
        assertEquals(user.getNickname(), result.get().getNickname());
        assertEquals(user.getHandle(), result.get().getHandle());
    }

}