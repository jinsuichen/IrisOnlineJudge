package fun.icpc.iris.application.command;
public record ChangePasswordCommand(
        String oldPassword,
        String newPassword
) {
}
