package com.ank.codeviewer.transport;

import com.ank.codeviewer.dto.UserDto;
import com.ank.codeviewer.mapper.SystemUserMapper;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.model.SystemUser;
import com.ank.codeviewer.sender.RequestStructure;
import com.ank.codeviewer.sender.RequestStructureBuilder;
import com.ank.codeviewer.sender.ResponseStructure;
import com.ank.codeviewer.sender.enums.RequestToServer;
import lombok.RequiredArgsConstructor;

import java.util.Base64;

/**
 * Отправляет запросы для регистрации, входа и обновления системного пользовтаеля
 * Системный пользователь это пользователь под которым происходит регистрация и работа(роли)
 */
@RequiredArgsConstructor
public class SenderSystemUserService {
    private final SenderService sender;
    private final RequestStructureBuilder builder;
    private final SystemUserMapper mapper;

    public ResponseService<SystemUser> createUser(SystemUser systemUser){
        RequestStructure structure = builder.build(RequestToServer.CREATE_USER);
        structure.setDtoToJson(mapper.mapToSystemUserDto(systemUser));

        ResponseStructure<UserDto> responseStructure = sender.send(structure,  UserDto.class);
        SystemUser user = null;

        if (responseStructure.isOk())
            user = mapper.mapToSystemUser(responseStructure.getDtoObject());

        return new ResponseService<SystemUser>(responseStructure.isOk(), responseStructure.getErrorMessage(), user);
    }

    public ResponseService<SystemUser> authenticationSystemUser(String login, String password) {
        RequestStructure structure = builder.build(RequestToServer.AUTHENTICATION);
        structure.addPathParam("login", login);
        structure.addHeader("Authorization", getBasicAuthenticationHeader(login, password));

        ResponseStructure<UserDto> responseStructure = sender.send(structure,  UserDto.class);
        SystemUser user = null;

        if (responseStructure.isOk())
            user = mapper.mapToSystemUser(responseStructure.getDtoObject());

        return new ResponseService<SystemUser>(responseStructure.isOk(), responseStructure.getErrorMessage(), user);
    }

    private String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
