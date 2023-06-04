package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.permission.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User Repository Tests
 */
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {

    /**
     * Test entity manager
     */
    @Autowired
    private TestEntityManager entityManager;

    /**
     * User repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Password encoder
     */
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Test find by id.
     */
    @Test
    public void testFindById() {
        // Insert a user into the database
        User user = new User();
        user.setNickname("nick");
        user.setHandle("handle");
        user.setPassword(passwordEncoder.encode("password"));
        user.setPassword("password");

        entityManager.persist(user);

        // Query the user from the database
        Optional<User> result = userRepository.findById(user.getUserId());
        assertTrue(result.isPresent());
        assertEquals(user.getNickname(), result.get().getNickname());
        assertEquals(user.getHandle(), result.get().getHandle());
    }

    /**
     * Test find by handle.
     */
    @Test
    public void testFindByHandle() {
        // Insert a user into the database
        User user = new User();
        user.setNickname("jack");
        user.setHandle("jack2020");
        user.setPassword(passwordEncoder.encode("password"));
        user.setPassword("password");

        entityManager.persist(user);

        // Query the user from the database
        Optional<User> result = userRepository.findByHandle(user.getHandle());
        assertTrue(result.isPresent());
        assertEquals(user.getNickname(), result.get().getNickname());
        assertEquals(user.getHandle(), result.get().getHandle());
    }


    /**
     * Test find by handle not exist.
     */
    @Test
    public void testFindByHandleNotExist() {
        // Query the user from the database
        Optional<User> result = userRepository.findByHandle("jack2020");
        assertTrue(result.isEmpty());
    }

    /**
     * Test find permission by handle.
     */
    @Test
    public void testFindPermissionByHandle() {

        // Create a permission
        Set<Permission> permissions = new HashSet<>();
        Permission permission = new Permission();
        permission.setName("ADMIN");
        entityManager.persist(permission);
        permissions.add(permission);

        // Insert a user into the database
        User user = new User();
        user.setNickname("jack");
        user.setHandle("jack2020");
        user.setPassword(passwordEncoder.encode("password"));
        user.setPassword("password");
        user.setPermissions(permissions);
        entityManager.persist(user);

        // Query the user from the database
        Optional<User> result = userRepository.findByHandle(user.getHandle());
        assertTrue(result.isPresent());
        assertEquals(user.getNickname(), result.get().getNickname());
        assertEquals(user.getHandle(), result.get().getHandle());
        assertEquals(user.getPermissions(), result.get().getPermissions());
    }

}