package com.ank.codeviewer.mapper;

import com.ank.codeviewer.dto.CreateUserDto;
import com.ank.codeviewer.dto.ShortUserDto;
import com.ank.codeviewer.dto.UserDto;
import com.ank.codeviewer.model.SystemUser;
import com.ank.codeviewer.model.User;
import com.ank.codeviewer.model.enums.TypeSystemUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMapper {
    public User mapToUser(SystemUser systemUser) {
        return new User(systemUser.getId(), systemUser.getLogin());
    }

    public User mapToShortUserDto(ShortUserDto shortUserDto) {
        return new User(shortUserDto.getId(), shortUserDto.getLogin());
    }
}
