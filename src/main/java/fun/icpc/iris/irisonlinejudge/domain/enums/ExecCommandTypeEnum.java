package fun.icpc.iris.irisonlinejudge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExecCommandTypeEnum {

    JAVA("JAVA",
        "touch /mv_exit_code; touch /compile_exit_code; touch /process_exit_code; touch /stderr; touch /stdout; touch /compile_info ; " +
        " mv /code /Main.java ; mv_exit_code=$? ; echo $mv_exit_code > /mv_exit_code ; " +
        " if [ $mv_exit_code -ne 0 ] ; then exit 1 ; fi ; " +
        " timeout 10s javac /Main.java 2> compile_info ; compile_exit_code=$? ; echo $compile_exit_code > /compile_exit_code ; " +
        " if [ $compile_exit_code -ne 0 ] ; then exit 1 ; fi ; " +
        " timeout %ss java Main < /input 2> /stderr > /stdout ; process_exit_code=$? ; echo $process_exit_code > /process_exit_code "),

    CPP("CPP",
        "touch /mv_exit_code; touch /compile_exit_code; touch /process_exit_code; touch /stderr; touch /stdout; touch /compile_info ; " +
        " mv /code /code.cpp ; mv_exit_code=$? ; echo $mv_exit_code > /mv_exit_code ; " +
        " if [ $mv_exit_code -ne 0 ] ; then exit 1 ; fi ; " +
        " timeout 10s g++ /code.cpp -o /out 2> compile_info ; compile_exit_code=$? ; echo $compile_exit_code > /compile_exit_code ; " +
        " if [ $compile_exit_code -ne 0 ] ; then exit 1 ; fi ; " +
        " timeout %ss /out < /input 2> /stderr > /stdout ; process_exit_code=$? ; echo $process_exit_code > /process_exit_code "),

    PYTHON("PYTHON3",
        "touch /mv_exit_code; touch /compile_exit_code; touch /process_exit_code; touch /stderr; touch /stdout; touch /compile_info ; " +
        " mv /code /code.py ; mv_exit_code=$? ; echo $mv_exit_code > /mv_exit_code ; " +
        " if [ $mv_exit_code -ne 0 ] ; then exit 1 ; fi ; " +
        " echo \"\" > /compile_info ; echo 0 > compile_exit_code ; " +
        " timeout %ss python3 /code.py < /input 2> /stderr > /stdout ; process_exit_code=$? ; echo $process_exit_code > /process_exit_code "),
    ;

    private final String language;
    private final String commandTemplate;

    public static ExecCommandTypeEnum fromLanguage(String language) {
        for (ExecCommandTypeEnum value : values()) {
            if (value.getLanguage().equals(language)) {
                return value;
            }
        }
        return null;
    }

    public String getCommand(String timeLimitStr) {
        return String.format(this.commandTemplate, timeLimitStr);
    }
}