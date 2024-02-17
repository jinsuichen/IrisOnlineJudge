package fun.icpc.iris.application.command;
public record RegisterCommand(
        String handle,
        String nickName,
        String password
) {
}
