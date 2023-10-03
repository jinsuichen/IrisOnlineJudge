package fun.icpc.iris.irisonlinejudge.controller;

import fun.icpc.iris.irisonlinejudge.commons.util.ConstraintValidator;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.domain.record.ChangeNickNameRequest;
import fun.icpc.iris.irisonlinejudge.domain.record.ChangePasswordRequest;
import fun.icpc.iris.irisonlinejudge.service.UserApplicationService;
import fun.icpc.iris.irisonlinejudge.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/me")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    private final UserDomainService userDomainService;

    @PostMapping("/logout")
    public IrisMessage<Boolean> logout() {
        return userApplicationService.logout();
    }

    @PostMapping("/logoutAll")
    public IrisMessage<Boolean> logoutAll() {
        return userApplicationService.logoutAll();
    }

    @PostMapping("/password")
    public IrisMessage<Boolean> changePassword(@RequestBody ChangePasswordRequest request) {
        if(ConstraintValidator.validatePassword(request.oldPassword()).isFail() ||
                ConstraintValidator.validatePassword(request.newPassword()).isFail()) {
            return IrisMessageFactory.fail("Invalid password.");
        }

        if(StringUtils.equals(request.oldPassword(), request.newPassword())) {
            return IrisMessageFactory.fail("The new password cannot be the same as the old password.");
        }

        return userApplicationService.changePassword(request.oldPassword(), request.newPassword());
    }

    @PostMapping("/nickName")
    public IrisMessage<Boolean> changeNickName(@RequestBody ChangeNickNameRequest request) {
        if(ConstraintValidator.validateNickname(request.nickName()).isFail()) {
            return IrisMessageFactory.fail("Invalid nickname.");
        }

        return userDomainService.changeNickName(request.nickName());
    }
}
