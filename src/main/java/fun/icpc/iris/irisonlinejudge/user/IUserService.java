package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.commons.Result;

public interface IUserService {
    Result<Void> addUser(User user);

    Result<User> queryUser(String handle);
}
