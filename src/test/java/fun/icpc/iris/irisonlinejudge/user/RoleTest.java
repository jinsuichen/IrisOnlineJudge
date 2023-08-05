package fun.icpc.iris.irisonlinejudge.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fun.icpc.iris.irisonlinejudge.domain.user.Role;
import org.junit.jupiter.api.Test;

class RoleTest {
    @Test
    void testGetAuthorities() {
        assertEquals(1, Role.USER.getAuthorities().size());
        assertEquals(5, Role.MANAGER.getAuthorities().size());
        assertEquals(9, Role.ADMIN.getAuthorities().size());
    }

}