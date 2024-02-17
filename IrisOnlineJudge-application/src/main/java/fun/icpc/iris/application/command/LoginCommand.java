package fun.icpc.iris.application.command;
public record LoginCommand(
        String handle,
        String password
) {
}
