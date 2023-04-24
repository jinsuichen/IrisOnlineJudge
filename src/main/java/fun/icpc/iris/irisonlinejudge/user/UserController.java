package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.commons.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/user")
    public Result<Void> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/user/{handle}")
    public Result<User> queryUser(@PathVariable String handle) {
        return userService.queryUserByHandle(handle);
    }

}
