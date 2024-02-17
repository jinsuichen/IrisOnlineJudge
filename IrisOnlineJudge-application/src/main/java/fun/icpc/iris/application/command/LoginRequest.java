package fun.icpc.iris.application.command;
public record LoginRequest(
        String handle,
        String password
) {
}
