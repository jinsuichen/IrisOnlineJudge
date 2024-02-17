package fun.icpc.iris.application.service.impl;

import fun.icpc.iris.application.service.applicationservice.AdminService;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserDomainServiceImpl userDomainService;

    @Override
    public IrisMessage<LocalDateTime> freezeUser(Long userId, Duration duration) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = now.plus(duration);
        IrisMessage<Void> message = userDomainService.updateUnfreezeTime(userId, target);
        if (!message.success()) {
            return IrisMessageFactory.success(target);
        } else {
            return IrisMessageFactory.fail(null);
        }
    }

    @Override
    public IrisMessage<LocalDateTime> freezeUser(Long userId, LocalDateTime unfreezeTime) {
        IrisMessage<Void> message = userDomainService.updateUnfreezeTime(userId, unfreezeTime);
        if (!message.success()) {
            return IrisMessageFactory.success(unfreezeTime);
        } else {
            return IrisMessageFactory.fail(null);
        }
    }
}
