package fun.icpc.iris.application.command;
public record ChangePasswordRequest(
        String oldPassword,
        String newPassword
) {
}
