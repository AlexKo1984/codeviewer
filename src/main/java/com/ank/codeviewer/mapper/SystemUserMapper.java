package com.ank.codeviewer.mapper;

import com.ank.codeviewer.dto.CreateUserDto;
import com.ank.codeviewer.dto.UserDto;
import com.ank.codeviewer.model.SystemUser;
import com.ank.codeviewer.model.enums.TypeSystemUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SystemUserMapper {
    public CreateUserDto mapToSystemUserDto(SystemUser systemUser) {
        return CreateUserDto.builder()
                .id(systemUser.getId())
                .name(systemUser.getName())
                .login(systemUser.getLogin())
                .password(systemUser.getPassword())
                .email(systemUser.getEmail())
                .typeUser(String.valueOf(systemUser.getTypeUser()))
                .build();
    }
    public SystemUser mapToSystemUser(UserDto userDto) {
        return SystemUser.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .login(userDto.getLogin())
                //.password(userDto.getPassword())
                .email(userDto.getEmail())
                .typeUser(TypeSystemUser.valueOf(userDto.getTypeUser()))
                .build();
    }
}
