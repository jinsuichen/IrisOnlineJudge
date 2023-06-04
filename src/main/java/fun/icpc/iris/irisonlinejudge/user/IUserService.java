package fun.icpc.iris.irisonlinejudge.user;

import fun.icpc.iris.irisonlinejudge.commons.Result;

public interface IUserService {

    /**
     * Add a user.
     * @param user The user to add.
     * @return The result.
     */
    Result<Void> addUser(User user);

    /**
     * Query a user by handle.
     * @param handle The handle of the user.
     * @return The result.
     */
    Result<User> queryUserByHandle(String handle);
}
