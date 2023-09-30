package fun.icpc.iris.irisonlinejudge.service;

public interface AuthenticationService {

    void register(String handle, String nickName, String password);

    void login(String handle, String password);

    void logout();
}
