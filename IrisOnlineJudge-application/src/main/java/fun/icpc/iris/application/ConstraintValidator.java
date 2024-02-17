package fun.icpc.iris.application;

import fun.icpc.iris.application.command.LoginRequest;
import fun.icpc.iris.application.command.RegisterRequest;
import fun.icpc.iris.application.dto.ProblemDTO;
import fun.icpc.iris.application.dto.TestCaseDTO;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
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
     * The regex for problem title.
     * The length must be between 1 and 200, any character is allowed.
     */
    public static final String PROBLEM_TITLE_REGEX = "^[\\s\\S]{1,200}$";

    /**
     * The message for problem title regex.
     */
    public static final String PROBLEM_TITLE_REGEX_MESSAGE = "The length must be between 1 and 200, any character is " +
            "allowed.";

    /**
     * The max time limit (s).
     */
    public static final Long MAX_TIME_LIMIT = 20L;

    /**
     * The min time limit (s).
     */
    public static final Long MIN_TIME_LIMIT = 1L;

    /**
     * The max memory limit (MB).
     */
    public static final Long MAX_MEMORY_LIMIT = 1024L;

    /**
     * The min memory limit (MB).
     */
    public static final Long MIN_MEMORY_LIMIT = 4L;

    /**
     * The max problem content length.
     */
    public static final Long MAX_PROBLEM_CONTENT_LENGTH = 10000000L;

    /**
     * The max testCase input/out length.
     */
    public static final Long MAX_TEST_CASE_LENGTH = 1000000000L;

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

    /**
     * Validate the tenant name.
     *
     * @param tenantName The tenant name to be validated.
     * @return If the tenant name is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateTenantName(String tenantName) {
        if (StringUtils.isEmpty(tenantName)) {
            return IrisMessageFactory.fail("The tenant name cannot be empty.");
        }
        if (!tenantName.matches(TENANT_NAME_REGEX)) {
            return IrisMessageFactory.fail(TENANT_NAME_REGEX_MESSAGE);
        }
        return IrisMessageFactory.success(true);
    }

    /**
     * Validate the problem title.
     *
     * @param problemTitle The problem title to be validated.
     * @return If the problem title is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateProblemTitle(String problemTitle) {
        if (StringUtils.isEmpty(problemTitle)) {
            return IrisMessageFactory.fail("The problem title cannot be empty.");
        }
        if (!problemTitle.matches(PROBLEM_TITLE_REGEX)) {
            return IrisMessageFactory.fail(PROBLEM_TITLE_REGEX_MESSAGE);
        }
        return IrisMessageFactory.success(true);
    }

    /**
     * Validate the problem content.
     *
     * @param problemContent The problem content to be validated.
     * @return If the problem content is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateProblemContent(String problemContent) {
        if (StringUtils.isEmpty(problemContent)) {
            return IrisMessageFactory.fail("The problem content cannot be empty.");
        }
        if (problemContent.length() > MAX_PROBLEM_CONTENT_LENGTH) {
            return IrisMessageFactory.fail("The problem content is too long, the max length is " + MAX_PROBLEM_CONTENT_LENGTH + ".");
        }
        return IrisMessageFactory.success(true);
    }

    /**
     * Validate the memory limit.
     *
     * @param timeLimit The memory limit to be validated.
     * @return If the memory limit is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateTimeLimit(Long timeLimit) {
        if (timeLimit == null) {
            return IrisMessageFactory.fail("The time limit cannot be empty.");
        }
        if (timeLimit < MIN_TIME_LIMIT) {
            return IrisMessageFactory.fail("The time limit is too short, the min time limit is " + MIN_TIME_LIMIT +
                    "s.");
        }
        if (timeLimit > MAX_TIME_LIMIT) {
            return IrisMessageFactory.fail("The time limit is too long, the max time limit is " + MAX_TIME_LIMIT + "s" +
                    ".");
        }
        return IrisMessageFactory.success(true);
    }


    /**
     * Validate the memory limit.
     *
     * @param memoryLimit The memory limit to be validated.
     * @return If the memory limit is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateMemoryLimit(Long memoryLimit) {
        if (memoryLimit == null) {
            return IrisMessageFactory.fail("The memory limit cannot be empty.");
        }
        if (memoryLimit < MIN_MEMORY_LIMIT) {
            return IrisMessageFactory.fail("The memory limit is too short, the min memory limit is " + MIN_MEMORY_LIMIT + "MB.");
        }
        if (memoryLimit > MAX_MEMORY_LIMIT) {
            return IrisMessageFactory.fail("The memory limit is too long, the max memory limit is " + MAX_MEMORY_LIMIT + "MB.");
        }
        return IrisMessageFactory.success(true);
    }

    /**
     * Validate the problem.
     *
     * @param problemDTO The problemDTO to be validated.
     * @return If the problem is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateProblem(ProblemDTO problemDTO) {
        IrisMessage<Boolean> titleValid = ConstraintValidator.validateProblemTitle(problemDTO.getTitle());
        if (!titleValid.success()) {
            return IrisMessageFactory.fail(titleValid.message());
        }

        IrisMessage<Boolean> contentValid = ConstraintValidator.validateProblemContent(problemDTO.getContent());
        if (!contentValid.success()) {
            return IrisMessageFactory.fail(contentValid.message());
        }

        IrisMessage<Boolean> timeLimitValid = ConstraintValidator.validateTimeLimit(problemDTO.getTimeLimit());
        if (!timeLimitValid.success()) {
            return IrisMessageFactory.fail(timeLimitValid.message());
        }

        IrisMessage<Boolean> memoryLimitValid = ConstraintValidator.validateMemoryLimit(problemDTO.getMemoryLimit());
        if (!memoryLimitValid.success()) {
            return IrisMessageFactory.fail(memoryLimitValid.message());
        }

        return IrisMessageFactory.success(true);
    }

    /**
     * Validate the test case.
     *
     * @param testCaseDTO The testCaseDTO to be validated.
     * @return If the test case is valid, return true, else return false.
     */
    public static IrisMessage<Boolean> validateTestCase(TestCaseDTO testCaseDTO) {
        String input = testCaseDTO.getInput();
        String output = testCaseDTO.getOutput();

        if (StringUtils.isEmpty(input)) {
            return IrisMessageFactory.fail("The input cannot be empty.");
        }
        if (input.length() > MAX_TEST_CASE_LENGTH) {
            return IrisMessageFactory.fail("The input is too long, the max length is " + MAX_TEST_CASE_LENGTH + ".");
        }
        if (StringUtils.isEmpty(output)) {
            return IrisMessageFactory.fail("The output cannot be empty.");
        }
        if (output.length() > MAX_TEST_CASE_LENGTH) {
            return IrisMessageFactory.fail("The output is too long, the max length is " + MAX_TEST_CASE_LENGTH + ".");
        }
        return IrisMessageFactory.success(true);
    }

}
