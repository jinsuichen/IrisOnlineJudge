package fun.icpc.iris.application.dto;

import fun.icpc.iris.sharedkernel.enums.GlobalUserRoleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String handle;

    private String nickname;

    private GlobalUserRoleTypeEnum role;

    private String loginUUID;
}
