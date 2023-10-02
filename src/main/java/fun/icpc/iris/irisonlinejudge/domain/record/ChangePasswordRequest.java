package fun.icpc.iris.irisonlinejudge.domain.record;

public record ChangePasswordRequest(
        String oldPassword,
        String newPassword
) {
}
