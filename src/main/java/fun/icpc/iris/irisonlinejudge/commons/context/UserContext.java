package fun.icpc.iris.irisonlinejudge.commons.context;

import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;

public class UserContext {

    public static final ThreadLocal<UserDTO> USER = new ThreadLocal<>();

    public static void set(UserDTO user) {
        USER.set(user);
    }

    public static void remove() {
        USER.remove();
    }

    public static UserDTO get() {
        return USER.get();
    }

}
