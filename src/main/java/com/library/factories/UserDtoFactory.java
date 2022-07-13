package com.library.factories;

import com.library.dto.UserDto;
import com.library.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDtoFactory {

    public UserDto makeUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .build();
    }
}
