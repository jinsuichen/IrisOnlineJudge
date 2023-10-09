package fun.icpc.iris.irisonlinejudge.service.applicationservice;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;

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
