package com.ank.codeviewer.action.posts;

import com.ank.codeviewer.clientservice.ClientSystemUserService;
import com.ank.codeviewer.model.Model;
import com.ank.codeviewer.model.enums.TypeSystemUser;
import javafx.scene.control.Button;
import javafx.stage.Window;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Управляет регистрацией, аутентификацией и выходом пользователя
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PostWindowAuthenticationSystemUser {
    private final ClientSystemUserService clientSystemUserService;
    private final Window owner;
    private Button btnSystemUser;
    private Button btnActionSystemUser;

    public void refresh() {
        if (isUnregisteredUser()){
            btnActionSystemUser.setText("Вход");
            setTextButtonColor(btnActionSystemUser, "GREEN");
        }else {
            btnActionSystemUser.setText("Выход");
            setTextButtonColor(btnActionSystemUser, "BLUE");
        }
        if (isUnregisteredUser()){
            setTextButtonColor(btnSystemUser, "RED");
        }else {
            setTextButtonColor(btnSystemUser, "BLUE");
        }
        btnSystemUser.setText(String.valueOf(Model.getInstance().getSystemUser()));
    }

    /**
     * Когда нажимаем на кнопку onSystemUserButtonClick
     * В зависемости от состояния происходит регистрация или показ информации о пользователе
     */
    public void handleSystemUser(){
        if (isUnregisteredUser()){
            clientSystemUserService.beginRegistratioNewSystemUser(owner);
        }else {
            clientSystemUserService.informationSystemUser(owner);
        }
    }

    /**
     * Вход или выход пользователя
     */
    public void enterExitSystemUser() {
        if (isUnregisteredUser()){
            clientSystemUserService.beginAuthenticationSystemUser(owner);
        }else {
            clientSystemUserService.exitSystemUser(owner, true);
        }
    }
    private boolean isUnregisteredUser(){
        return Model.getInstance().getSystemUser().getTypeUser() == TypeSystemUser.UNREGISTERED_USER;
    }

    private void setTextButtonColor(Button button, String strColor){
        button.setStyle("-fx-text-fill: " + strColor);
    }

    public void clearCurrentAuthentication() {
        clientSystemUserService.exitSystemUser(owner, false);
    }
}
