package fun.icpc.iris.irisonlinejudge.controller;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.record.ChangeNickNameRequest;
import fun.icpc.iris.irisonlinejudge.domain.record.ChangePasswordRequest;
import fun.icpc.iris.irisonlinejudge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/logout")
    public IrisMessage<Boolean> logout() {
        return userService.logout();
    }

    @PostMapping("/logoutAll")
    public IrisMessage<Boolean> logoutAll() {
        return userService.logoutAll();
    }

    @PostMapping("/password")
    public IrisMessage<Boolean> changePassword(@RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request.oldPassword(), request.newPassword());
    }

    @PostMapping("/nickName")
    public IrisMessage<Boolean> changeNickName(@RequestBody ChangeNickNameRequest request) {
        return userService.changeNickName(request.nickName());
    }
}
