package fun.icpc.iris.irisonlinejudge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Judger type enum.
 */
@Getter
@AllArgsConstructor
public enum JudgerTypeEnum {

    /**
     * character by character judge.
     */
    EXACT("CHARACTER_BY_CHARACTER_JUDGE") {
        @Override
        public boolean isAccepted(String output, String answer) {
            return output.equals(answer);
        }
    },

    /**
     * special judge.
     */
    SPECIAL("SPECIAL_JUDGE") {
        @Override
        public boolean isAccepted(String output, String answer) {
            return false;
        }
    },

    /**
     * ignore whitespace and newline judge.
     */
    WHITESPACE_IGNORING("IGNORE_WHITESPACE_AND_NEWLINE_JUDGE") {
        @Override
        public boolean isAccepted(String output, String answer) {
            return removeBlankCharacters(output).equals(removeBlankCharacters(answer));
        }
    },

    ;

    private final String description;

    public abstract boolean isAccepted(String output, String answer);

    public static JudgerTypeEnum fromType(String type) {
        for (JudgerTypeEnum value : values()) {
            if (value.getDescription().equals(type)) {
                return value;
            }
        }
        return null;
    }

    /**
     * Remove blank characters at the end of lines
     * and blank lines at the end of the total string.
     *
     * @return the string
     */
    protected String removeBlankCharacters(String str) {
        String[] lines = str.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line.replaceAll("\\s+$", ""));
            sb.append("\n");
        }
        return sb.toString().replaceAll("\\n+$", "");
    }
}
