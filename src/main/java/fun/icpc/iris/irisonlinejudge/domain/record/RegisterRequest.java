package fun.icpc.iris.irisonlinejudge.domain.record;

public record RegisterRequest(
        String handle,
        String nickName,
        String password
) {
}
