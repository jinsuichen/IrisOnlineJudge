package fun.icpc.iris.application.command;
public record RegisterRequest(
        String handle,
        String nickName,
        String password
) {
}
