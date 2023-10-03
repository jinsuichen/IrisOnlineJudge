package fun.icpc.iris.irisonlinejudge.domain.dto;

import fun.icpc.iris.irisonlinejudge.domain.enums.UserRoleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String handle;

    private String nickname;

    private UserRoleTypeEnum role;

    private String loginUUID;
}
