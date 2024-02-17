package fun.icpc.iris.application.service.applicationservice;

import fun.icpc.iris.sharedkernel.util.IrisMessage;

import java.time.Duration;
import java.time.LocalDateTime;

public interface AdminService {

    /**
     * Freeze user for a period of time
     *
     * @param userId user id
     * @return unfreeze time
     */
    IrisMessage<LocalDateTime> freezeUser(Long userId, Duration duration);

    /**
     * Freeze user until unfreeze time
     *
     * @param userId       user id
     * @param unfreezeTime unfreeze time
     * @return unfreeze time
     */
    IrisMessage<LocalDateTime> freezeUser(Long userId, LocalDateTime unfreezeTime);


}
