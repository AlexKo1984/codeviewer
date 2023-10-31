package com.ank.codeviewer.service;

import com.ank.codeviewer.model.Model;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.model.SystemUser;
import com.ank.codeviewer.transport.SenderSystemUserService;
import lombok.RequiredArgsConstructor;

/**
 * Обработка регистрации, входа и выхода пользователя
 */
@RequiredArgsConstructor
public class SystemUserService {
    private final SenderSystemUserService senderSystemUserService;

    public ResponseService<SystemUser> registratioNewSystemUser(SystemUser systemUser) {
        ResponseService<SystemUser> response = senderSystemUserService.createUser(systemUser);

        if (response.isOk()){
            setSystemUser(systemUser);
        }

        return response;
    }

    /**
     * Выход пользователя из системы
     */
    public void exitSystemUser() {
        Model.getInstance().setSystemUser(SystemUser.getUnregisteredUser());
    }

    public void setSystemUser(SystemUser user) {
        Model.getInstance().setSystemUser(user);
    }

    public ResponseService<SystemUser> authenticationSystemUser(String login, String password) {
        ResponseService<SystemUser> response = senderSystemUserService.authenticationSystemUser(login, password);

        if (response.isOk()){
            SystemUser systemUser = response.getObject();
            systemUser.setPassword(password);
            setSystemUser(systemUser);
        }

        return response;
    }
}
