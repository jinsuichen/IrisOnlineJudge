package fun.icpc.iris.irisonlinejudge.controller;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.enums.GlobalUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.service.AdminService;
import fun.icpc.iris.irisonlinejudge.service.impl.AuthorizationGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/user/{userId}/freeze/{hourDuration}")
    @AuthorizationGlobal(GlobalUserRoleTypeEnum.ADMIN)
    public IrisMessage<LocalDateTime> freezeUser(@PathVariable Long userId,
                                                 @PathVariable Long hourDuration) {
        Duration duration = Duration.ofHours(hourDuration);
        return adminService.freezeUser(userId, duration);
    }

}
