package fun.icpc.iris.adapter.rest.controller;

import fun.icpc.iris.application.service.aop.AuthGlobal;
import fun.icpc.iris.application.service.applicationservice.AdminService;
import fun.icpc.iris.sharedkernel.enums.GlobalUserRoleTypeEnum;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
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
    @AuthGlobal(GlobalUserRoleTypeEnum.ADMIN)
    public IrisMessage<LocalDateTime> freezeUser(@PathVariable Long userId,
                                                 @PathVariable Long hourDuration) {
        Duration duration = Duration.ofHours(hourDuration);
        return adminService.freezeUser(userId, duration);
    }

}
