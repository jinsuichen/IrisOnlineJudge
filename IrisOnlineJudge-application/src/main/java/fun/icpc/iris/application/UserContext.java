package fun.icpc.iris.application;

import fun.icpc.iris.application.dto.UserDTO;

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
