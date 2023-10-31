package com.ank.codeviewer.model;

import com.ank.codeviewer.model.enums.TypeSystemUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Пользователь для регистрации
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemUser {
    private static final SystemUser unregisteredUser = SystemUser.builder().id(-1).login("Регистрация").typeUser(TypeSystemUser.UNREGISTERED_USER).build();
    private Integer id;
    private String name;
    private String login;
    private String password;
    private String email;
    private TypeSystemUser typeUser;
    public static SystemUser getUnregisteredUser(){
        return unregisteredUser;
    }

    @Override
    public String toString() {
        if (typeUser == TypeSystemUser.UNREGISTERED_USER)
            return login;
        else
            return login + "(" + String.valueOf(typeUser) + ")";
    }
}