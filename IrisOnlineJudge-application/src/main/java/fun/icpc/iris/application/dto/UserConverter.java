package fun.icpc.iris.application.dto;


import fun.icpc.iris.domain.entity.table.UserEntity;
import org.apache.commons.lang3.StringUtils;
public class UserConverter {

    public static UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .handle(userEntity.getHandle())
                .nickname(userEntity.getNickName())
                .role(userEntity.getRole())
                .loginUUID(StringUtils.EMPTY)
                .build();
    }
}
