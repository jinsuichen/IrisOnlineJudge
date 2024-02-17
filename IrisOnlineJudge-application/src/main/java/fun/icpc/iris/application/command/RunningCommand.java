package fun.icpc.iris.application.command;
import lombok.Builder;

@Builder
public record RunningCommand(String code, String input) {
}
