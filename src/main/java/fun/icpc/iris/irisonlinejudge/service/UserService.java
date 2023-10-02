package fun.icpc.iris.irisonlinejudge.service;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;

public interface UserService {

    /**
     * Register a new user.
     *
     * @param handle   The handle of the user.
     * @param nickName The nickname of the user.
     * @param password The password of the user.
     * @return If register successfully, return true, else return false.
     */
    IrisMessage<String> register(String handle, String nickName, String password);

    /**
     * Login with handle and password.
     *
     * @param handle   The handle of the user.
     * @param password The password of the user.
     * @return The token of the user for login.
     */
    IrisMessage<String> login(String handle, String password);

    /**
     * Logout.
     * Only current client will be logout.
     *
     * @return If logout successfully, return true, else return false.
     */
    IrisMessage<Boolean> logout();

    /**
     * Logout.
     * All clients will be logout.
     *
     * @return If logout successfully, return true, else return false.
     */
    IrisMessage<Boolean> logoutAll();

    /**
     * Check whether the user has logged in.
     *
     * @return If the user has logged in, return true, else return false.
     */
    IrisMessage<Boolean> checkLogin();

    /**
     * Get the handle of the user.
     *
     * @param oldPassword The old password of the user.
     * @param newPassword The new password of the user.
     * @return If change password successfully, return true, else return false.
     */
    IrisMessage<Boolean> changePassword(String oldPassword, String newPassword);

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

}
