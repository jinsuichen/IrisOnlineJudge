package fun.icpc.iris.irisonlinejudge.problem;

/**
 * The enum Judger type enum.
 */
public enum JudgerTypeEnum {

    /**
     * character by character judge.
     */
    LOCAL("CHARACTER_BY_CHARACTER_JUDGE"),

    /**
     * special judge.
     */
    SPECIAL("SPECIAL_JUDGE"),

    /**
     * ignore whitespace and newline judge.
     */
    STANDARD("IGNORE_WHITESPACE_AND_NEWLINE_JUDGE"),

    ;

    private final String type;

    JudgerTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static JudgerTypeEnum fromType(String type) {
        for (JudgerTypeEnum value : values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }

}
