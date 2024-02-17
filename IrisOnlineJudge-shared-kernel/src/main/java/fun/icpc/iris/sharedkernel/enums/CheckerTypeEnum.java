package fun.icpc.iris.sharedkernel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * The enum Judger type enum.
 */
@Getter
@AllArgsConstructor
public enum CheckerTypeEnum {

    /**
     * character by character judge.
     */
    EXACT("Check character by character") {
        @Override
        public boolean isAccepted(String output, String answer) {
            return output.equals(answer);
        }
    },

    /**
     * ignore whitespace and newline judge.
     */
    WHITESPACE_IGNORING("Ignore whitespace and newline, check token by token") {
        @Override
        public boolean isAccepted(String output, String answer) {
            return removeBlankCharacters(output).equals(removeBlankCharacters(answer));
        }
    },

    /**
     * Compares output and answer as sequence of tokens. Ignores whitespaces mismatchings.
     */
    SEQUENCE("Compare output and answer as sequence of tokens. Ignores whitespaces mismatchings.") {
        @Override
        public boolean isAccepted(String output, String answer) {
            List<String> outPutTokens = splitToTokens(output);
            List<String> answerTokens = splitToTokens(answer);
            return outPutTokens.equals(answerTokens);
        }
    },

    /**
     * Float judge, max error is 1e-4.
     */
    FLOAT_4("Compare output and answer as a sequence of real numbers. Ignores whitespaces mismatchings. Two real " +
            "numbers are considered equals if their absolute or relative error doesn't exceed 1E-4.") {
        @Override
        public boolean isAccepted(String output, String answer) {
            List<String> outPutTokens = splitToTokens(output);
            List<String> answerTokens = splitToTokens(answer);
            if (outPutTokens.size() != answerTokens.size()) {
                return false;
            }
            for (int i = 0; i < outPutTokens.size(); i++) {
                if (!doubleCompare(
                        Double.parseDouble(outPutTokens.get(i)),
                        Double.parseDouble(answerTokens.get(i)),
                        1e-4)) {
                    return false;
                }
            }
            return true;
        }
    },

    /**
     * Float judge, max error is 1e-6.
     */
    FLOAT_6("Compare output and answer as a sequence of real numbers. Ignores whitespaces mismatchings. Two real " +
            "numbers are considered equals if their absolute or relative error doesn't exceed 1E-6.") {
        @Override
        public boolean isAccepted(String output, String answer) {
            List<String> outPutTokens = splitToTokens(output);
            List<String> answerTokens = splitToTokens(answer);
            if (outPutTokens.size() != answerTokens.size()) {
                return false;
            }
            for (int i = 0; i < outPutTokens.size(); i++) {
                if (!doubleCompare(
                        Double.parseDouble(outPutTokens.get(i)),
                        Double.parseDouble(answerTokens.get(i)),
                        1e-6)) {
                    return false;
                }
            }
            return true;
        }
    },

    /**
     * Float judge, max error is 1e-9.
     */
    FLOAT_9("Compare output and answer as a sequence of real numbers. Ignores whitespaces mismatchings. Two real " +
            "numbers are considered equals if their absolute or relative error doesn't exceed 1E-9.") {
        @Override
        public boolean isAccepted(String output, String answer) {
            List<String> outPutTokens = splitToTokens(output);
            List<String> answerTokens = splitToTokens(answer);
            if (outPutTokens.size() != answerTokens.size()) {
                return false;
            }
            for (int i = 0; i < outPutTokens.size(); i++) {
                if (!doubleCompare(
                        Double.parseDouble(outPutTokens.get(i)),
                        Double.parseDouble(answerTokens.get(i)),
                        1e-9)) {
                    return false;
                }
            }
            return true;
        }
    },

    /**
     * User defined checker.
     */
    USER_DEFINED("User defined checker"),

    /**
     * Interactive judge.
     */
    INTERACTIVE("Interactive judge");

    private final String description;

    public boolean isAccepted(String output, String answer) {
        return false;
    }

    public static CheckerTypeEnum fromType(String type) {
        for (CheckerTypeEnum value : values()) {
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

    /**
     * Split the string to tokens.
     *
     * @return the list of tokens
     */
    protected List<String> splitToTokens(String str) {
        return Arrays.stream(str.split("\\s+")).toList();
    }

    /**
     * Compare two double numbers.
     * Two real numbers are considered equals if their absolute or relative error doesn't exceed maxError.
     *
     * @param expected expected value
     * @param actual   actual value
     * @param maxError max error
     * @return true if two double numbers are considered equals
     */
    protected boolean doubleCompare(double expected, double actual, double maxError) {
        maxError += 1e-15;
        if (Double.isNaN(expected)) {
            return Double.isNaN(actual);
        }
        if (Double.isInfinite(expected)) {
            return Double.isInfinite(actual) && Math.signum(expected) == Math.signum(actual);
        }
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            return false;
        }
        if (Math.abs(expected - actual) <= maxError) {
            return true;
        }

        double minValue = Math.min(expected * (1.0d - maxError), expected * (1.0d + maxError));
        double maxValue = Math.max(expected * (1.0d - maxError), expected * (1.0d + maxError));

        return actual >= minValue && actual <= maxValue;
    }
}
