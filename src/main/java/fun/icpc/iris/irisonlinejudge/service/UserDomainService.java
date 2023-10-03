package fun.icpc.iris.irisonlinejudge.service;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;
import fun.icpc.iris.irisonlinejudge.domain.enums.GlobalUserRoleTypeEnum;
import fun.icpc.iris.irisonlinejudge.domain.enums.TenantUserRoleTypeEnum;

import java.time.LocalDateTime;
import java.util.List;

public interface UserDomainService {

    /**
     * Change the nickname of the user.
     *
     * @param newNickName The new nickname of the user.
     * @return If change nickname successfully, return true, else return false.
     */
    IrisMessage<Boolean> changeNickName(String newNickName);

    /**
     * Change the avatar of the user.
     *
     * @param newAvatar The new avatar of the user.
     * @return If change avatar successfully, return true, else return false.
     */
    IrisMessage<Boolean> changeAvatar(String newAvatar);

    /**
     * Get the global role of the user.
     *
     * @param handle The handle of the user.
     * @return The global role of the user.
     */
    IrisMessage<GlobalUserRoleTypeEnum> getGlobalUserRole(String handle);

    /**
     * Get the tenant role of the user.
     *
     * @param userId   The id of the user.
     * @param tenantId The id of the tenant.
     * @return The tenant role of the user.
     */
    IrisMessage<List<TenantUserRoleTypeEnum>> getTenantUserRole(Long userId, Long tenantId);

    /**
     * Freeze user for a period of time
     *
     * @param userId       user id
     * @param unfreezeTime unfreeze time
     * @return is success
     */
    IrisMessage<Void> updateUnfreezeTime(Long userId, LocalDateTime unfreezeTime);
}
