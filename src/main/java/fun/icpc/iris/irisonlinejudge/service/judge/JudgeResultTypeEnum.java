package fun.icpc.iris.irisonlinejudge.service.judge;


public enum JudgeResultTypeEnum {

    ACCEPT(0, "Accepted"),
    WRONG_ANSWER(1, "Wrong Answer"),
    TIME_LIMIT_EXCEEDED(2, "Time Limit Exceeded"),
    MEMORY_LIMIT_EXCEEDED(3, "Memory Limit Exceeded"),
    RUNTIME_ERROR(4, "Runtime Error"),
    COMPILE_ERROR(5, "Compile Error"),
    SYSTEM_ERROR(6, "System Error"),
    PENDING(7, "Pending"),
    JUDGING(8, "Judging"),
    PARTIALLY_ACCEPTED(9, "Partially Accepted"),
    OUTPUT_LIMIT_EXCEEDED(10, "Output Limit Exceeded"),
    PRESENTATION_ERROR(11, "Presentation Error"),
    IDLENESS_LIMIT_EXCEEDED(12, "Idleness Limit Exceeded"),
    SECURITY_VIOLATED(13, "Security Violated"),
    CRASHED(14, "Crashed"),
    INPUT_LIMIT_EXCEEDED(15, "Input Limit Exceeded"),
    CHALLENGED(16, "Challenged"),
    SKIPPED(17, "Skipped"),
    TESTING(18, "Testing"),
    REJECTED(19, "Rejected"),
    ;

    private final int code;
    private final String message;

    JudgeResultTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static JudgeResultTypeEnum fromCode(int code) {
        for (JudgeResultTypeEnum value : JudgeResultTypeEnum.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
