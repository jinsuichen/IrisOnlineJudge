package fun.icpc.iris.irisonlinejudge.domain.converter;

import fun.icpc.iris.irisonlinejudge.commons.exception.irisexception.NoSuchUserException;
import fun.icpc.iris.irisonlinejudge.domain.dto.UserDTO;
import fun.icpc.iris.irisonlinejudge.domain.entity.UserEntity;
import fun.icpc.iris.irisonlinejudge.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final UserRepository userRepository;

    public UserEntity toEntity(UserDTO userDTO) {
        return userRepository.findByHandle(userDTO.getHandle())
                .orElseThrow(NoSuchUserException::new);
    }

    public UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .handle(userEntity.getHandle())
                .nickname(userEntity.getNickName())
                .role(userEntity.getRole())
                .loginUUID(StringUtils.EMPTY)
                .build();
    }
}
