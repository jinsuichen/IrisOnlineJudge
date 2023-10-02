package fun.icpc.iris.irisonlinejudge.controller;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/user/register")
    public IrisMessage<Boolean> register(
            @RequestBody String handle,
            @RequestBody String nickName,
            @RequestBody String password) {
        return userService.register(handle, nickName, password);
    }

    @PostMapping("/user/login")
    public IrisMessage<String> login(
            @RequestBody String handle,
            @RequestBody String password) {
        return userService.login(handle, password);
    }
}
