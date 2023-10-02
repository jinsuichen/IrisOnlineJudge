package fun.icpc.iris.irisonlinejudge.controller;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.record.ChangePasswordRequest;
import fun.icpc.iris.irisonlinejudge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/logout")
    public IrisMessage<Boolean> logout() {
        return userService.logout();
    }

    @GetMapping("/login")
    public IrisMessage<Boolean> checkLogin() {
        return userService.checkLogin();
    }

    @PostMapping("/password")
    public IrisMessage<Boolean> changePassword(@RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request.oldPassword(), request.newPassword());
    }

    @PostMapping("/nickName")
    public IrisMessage<Boolean> changeNickName(@RequestBody String nickName) {
        return userService.changeNickName(nickName);
    }
}
