package fun.icpc.iris.irisonlinejudge.commons.context;

import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

    public static final ThreadLocal<UserDTO> USER = new ThreadLocal<>();

    public static void setUser(UserDTO user) {
        USER.set(user);
    }

    public static UserDTO getUser() {
        return USER.get();
    }

    public static void removeUser() {
        USER.remove();
    }

}
