package fun.icpc.iris.irisonlinejudge.controller;

import fun.icpc.iris.irisonlinejudge.commons.util.ConstraintValidator;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.record.LoginRequest;
import fun.icpc.iris.irisonlinejudge.domain.record.RegisterRequest;
import fun.icpc.iris.irisonlinejudge.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserApplicationService userApplicationService;

    @PostMapping("/user/register")
    public IrisMessage<String> register(@RequestBody RegisterRequest request) {
        if(ConstraintValidator.validateRegister(request).isFail()) {
            return IrisMessageFactory.fail("Invalid register request.");
        }

        return userApplicationService.register(
                request.handle(),
                request.nickName(),
                request.password());
    }

    @PostMapping("/user/login")
    public IrisMessage<String> login(@RequestBody LoginRequest request) {
        if(ConstraintValidator.validateLogin(request).isFail()) {
            return IrisMessageFactory.fail("Invalid register request.");
        }

        return userApplicationService.login(
                request.handle(),
                request.password());
    }

    @GetMapping("/user/login")
    public IrisMessage<Boolean> checkLogin() {
        return userApplicationService.checkLogin();
    }

}
