package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.commons.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User Service Tests
 */
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

    /**
     * User Service
     */
    @Resource
    private IUserService userService;

    /**
     * Test add user.
     */
    @Test
    public void testAddUser() {
        User user = new User();
        user.setHandle("test_add");
        user.setNickname("Test");
        user.setPassword("test");

        // Test add 2 same users.
        Result<Void> voidResult1 = userService.addUser(user);
        assertTrue(voidResult1.isSuccess());
        Result<Void> voidResult2 = userService.addUser(user);
        assertFalse(voidResult2.isSuccess());
    }


    /**
     * Test query user by handle.
     */
    @Test
    public void testQueryUserByHandle() {
        // Add a test user.
        User user = new User();
        user.setHandle("test_query");
        user.setNickname("Test");
        user.setPassword("test");
        Result<Void> voidResult = userService.addUser(user);
        assertTrue(voidResult.isSuccess());

        // Query the test user.
        Result<User> result = userService.queryUserByHandle("test_query");
        assertEquals(result.getData().getHandle(), user.getHandle());
        assertEquals(result.getData().getNickname(), user.getNickname());
    }

}
