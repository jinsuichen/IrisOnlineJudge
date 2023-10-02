package fun.icpc.iris.irisonlinejudge.commons.context;

import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;

public class UserContext {

    public static final ThreadLocal<UserDTO> USER = new ThreadLocal<>();

    public static final ThreadLocal<String> USER_TOKEN = new ThreadLocal<>();

    public static void setUser(UserDTO user, String token) {
        USER.set(user);
        USER_TOKEN.set(token);
    }

    public static void removeUser() {
        USER.remove();
        USER_TOKEN.remove();
    }

    public static UserDTO getUser() {
        return USER.get();
    }

    public static String getUserToken() {
        return USER_TOKEN.get();
    }

}
