package fun.icpc.iris.irisonlinejudge.user;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Resource
    private IUserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setHandle("test_system");
        user.setNickname("Test System");
        user.setPassword("test");
        userService.addUser(user);
    }

}
