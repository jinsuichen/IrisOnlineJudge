package fun.icpc.iris.irisonlinejudge.commons.util;

import fun.icpc.iris.irisonlinejudge.domain.record.LoginRequest;
import fun.icpc.iris.irisonlinejudge.domain.record.RegisterRequest;
import org.apache.commons.lang3.StringUtils;

public class ConstraintValidator {

    /**
     * The regex for handle.
     * Only lowercase letters, numbers and underscores are allowed.
     * The length must be between 1 and 20.
     */
    public static final String HANDLE_REGEX = "^[a-z0-9_]{1,20}$";

    /**
     * The message for handle regex.
     */
    public static final String HANDLE_REGEX_MESSAGE = "The handle can only contain lowercase letters, numbers and " +
            "underscores, and the length must be between 1 and 20.";

    /**
     * The regex for tenant name.
     * The length must be between 1 and 20, any character is allowed.
     */
    public static final String TENANT_NAME_REGEX = "^[\\s\\S]{1,20}$";

    /**
     * The message for tenant name regex.
     */
    public static final String TENANT_NAME_REGEX_MESSAGE = "The length must be between 1 and 20, any character is " +
            "allowed.";

    /**
     * The regex for password.
     * Must contain at least one uppercase letter, one lowercase letter and one number.
     * The length must be between 8 and 60.
     */
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,60}$";

    /**
     * The message for password regex.
     */
    public static final String PASSWORD_REGEX_MESSAGE = "The password must contain at least one uppercase letter, one" +
            " lowercase letter and one number, and the length must be between 8 and 60.";

    /**
     * The regex for nickname.
     * The length must be between 1 and 20, any character is allowed.
     */
    public static final String NICKNAME_REGEX = "^[\\s\\S]{1,20}$";

    /**
     * The message for nickname regex.
     */
    public static final String NICKNAME_REGEX_MESSAGE = "The length must be between 1 and 20, any character is " +
            "allowed.";

    /**
     * Validate the register request.
     *
     * @param request The register request.
     * @return If the register request is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateRegister(RegisterRequest request) {
        IrisMessage<Boolean> handleMessage = validateHandle(request.handle());
        if (!handleMessage.success()) {
            return handleMessage;
        }
        IrisMessage<Boolean> nicknameMessage = validateNickname(request.nickName());
        if (!nicknameMessage.success()) {
            return nicknameMessage;
        }
        IrisMessage<Boolean> passwordMessage = validatePassword(request.password());
        if (!passwordMessage.success()) {
            return passwordMessage;
        }
        return IrisMessageFactory.success(true);
    }

    /**
     * Validate the login request.
     *
     * @param request The login request.
     * @return If the login request is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateLogin(LoginRequest request) {
        IrisMessage<Boolean> handleMessage = validateHandle(request.handle());
        if (!handleMessage.success()) {
            return handleMessage;
        }
        IrisMessage<Boolean> passwordMessage = validatePassword(request.password());
        if (!passwordMessage.success()) {
            return passwordMessage;
        }
        return IrisMessageFactory.success(true);
    }

    /**
     * The regex for handle.
     *
     * @param handle The handle to be validated.
     * @return If the handle is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateHandle(String handle) {
        if (StringUtils.isEmpty(handle)) {
            return IrisMessageFactory.fail("The handle cannot be empty.");
        }
        if (!handle.matches(HANDLE_REGEX)) {
            return IrisMessageFactory.fail(HANDLE_REGEX_MESSAGE);
        }
        return IrisMessageFactory.success(true);
    }

    /**
     * The regex for password.
     *
     * @param password The password to be validated.
     * @return If the password is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            return IrisMessageFactory.fail("The password cannot be empty.");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            return IrisMessageFactory.fail(PASSWORD_REGEX_MESSAGE);
        }
        return IrisMessageFactory.success(true);
    }

    /**
     * The regex for nickname.
     *
     * @param nickname The nickname to be validated.
     * @return If the nickname is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateNickname(String nickname) {
        if (StringUtils.isEmpty(nickname)) {
            return IrisMessageFactory.fail("The nickname cannot be empty.");
        }
        if (!nickname.matches(NICKNAME_REGEX)) {
            return IrisMessageFactory.fail(NICKNAME_REGEX_MESSAGE);
        }
        return IrisMessageFactory.success(true);
    }

    public static IrisMessage<Boolean> validateTenantName(String tenantName) {
        if (StringUtils.isEmpty(tenantName)) {
            return IrisMessageFactory.fail("The tenant name cannot be empty.");
        }
        if (!tenantName.matches(TENANT_NAME_REGEX)) {
            return IrisMessageFactory.fail(TENANT_NAME_REGEX_MESSAGE);
        }
        return IrisMessageFactory.success(true);
    }

}
