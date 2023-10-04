package fun.icpc.iris.irisonlinejudge.domain.converter;

import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.UserEntity;
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
