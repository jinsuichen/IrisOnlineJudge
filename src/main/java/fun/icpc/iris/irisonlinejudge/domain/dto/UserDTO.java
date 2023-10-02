package fun.icpc.iris.irisonlinejudge.domain.dto;

import fun.icpc.iris.irisonlinejudge.domain.enums.RoleTypeEnum;
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

    private RoleTypeEnum role;

    private String loginUUID;
}
