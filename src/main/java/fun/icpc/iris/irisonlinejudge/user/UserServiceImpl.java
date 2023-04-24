package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.commons.Result;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Result<Void> addUser(User user) {
        boolean success = queryUserByHandle(user.getHandle()).isSuccess();
        if(success) {
            return Result.fail("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return Result.success();
    }

    @Override
    public Result<User> queryUserByHandle(String handle) {
        User user = userRepository.findByHandle(handle).orElse(null);
        if (user == null) {
            return Result.fail("User not found");
        }
        return Result.success(user);
    }
}
