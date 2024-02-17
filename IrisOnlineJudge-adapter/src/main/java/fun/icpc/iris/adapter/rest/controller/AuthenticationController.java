package fun.icpc.iris.adapter.rest.controller;

import fun.icpc.iris.application.ConstraintValidator;
import fun.icpc.iris.application.command.LoginCommand;
import fun.icpc.iris.application.command.RegisterCommand;
import fun.icpc.iris.application.service.applicationservice.UserApplicationService;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserApplicationService userApplicationService;

    @PostMapping("/user/register")
    public IrisMessage<String> register(@RequestBody RegisterCommand request) {
        if (!ConstraintValidator.validateRegister(request).success()) {
            return IrisMessageFactory.fail("Invalid register request.");
        }

        return userApplicationService.register(
                request.handle(),
                request.nickName(),
                request.password());
    }

    @PostMapping("/user/login")
    public IrisMessage<String> login(@RequestBody LoginCommand request) {
        if (!ConstraintValidator.validateLogin(request).success()) {
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
