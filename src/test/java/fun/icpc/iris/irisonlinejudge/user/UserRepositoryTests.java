package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.permission.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
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

    @Test
    public void testFindByHandle() {
        // Insert a user into the database
        User user = new User();
        user.setNickname("jack");
        user.setHandle("jack2020");
        entityManager.persist(user);

        // Query the user from the database
        Optional<User> result = userRepository.findByHandle(user.getHandle());
        assertTrue(result.isPresent());
        assertEquals(user.getNickname(), result.get().getNickname());
        assertEquals(user.getHandle(), result.get().getHandle());
    }


    @Test
    public void testFindByHandleNotExist() {
        // Query the user from the database
        Optional<User> result = userRepository.findByHandle("jack2020");
        assertTrue(result.isEmpty());
    }

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