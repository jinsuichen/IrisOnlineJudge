package fun.icpc.iris.irisonlinejudge.service.impl;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessageFactory;
import fun.icpc.iris.irisonlinejudge.service.AdminService;
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
        if(message.isSuccess()) {
            return IrisMessageFactory.success(target);
        } else {
            return IrisMessageFactory.fail(null);
        }
    }

    @Override
    public IrisMessage<LocalDateTime> freezeUser(Long userId, LocalDateTime unfreezeTime) {
        IrisMessage<Void> message = userDomainService.updateUnfreezeTime(userId, unfreezeTime);
        if(message.isSuccess()) {
            return IrisMessageFactory.success(unfreezeTime);
        } else {
            return IrisMessageFactory.fail(null);
        }
    }
}
