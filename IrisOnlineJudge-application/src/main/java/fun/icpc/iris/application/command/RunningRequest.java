package fun.icpc.iris.application.command;
import lombok.Builder;

@Builder
public record RunningRequest(String code, String input) {
}
