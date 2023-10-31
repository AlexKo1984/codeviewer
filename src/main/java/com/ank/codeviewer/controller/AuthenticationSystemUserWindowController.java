package com.ank.codeviewer.controller;

import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.model.SystemUser;
import com.ank.codeviewer.service.SystemUserService;
import com.ank.codeviewer.view.Dialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Форма аутентификации пользователя
 */
@Setter
public class AuthenticationSystemUserWindowController extends BaseWindowController{
    private SystemUserService systemUserService;
    @FXML public TextField txtLogin;
    @FXML public TextField txtPassword;
    private Dialogs dialogs;

    public void onHandleAuthenticationButtonClick(ActionEvent actionEvent) {
        if (!checkFields())
            return;

        ResponseService<SystemUser> response = systemUserService.authenticationSystemUser(getLogin(), getPassword());

        if (response.isOk())
            close();
        else
            dialogs.error(response.getErrorMessage());
    }

    private String getLogin() {
        return txtLogin.getText();
    }
    private String getPassword() {
        return txtPassword.getText();
    }

    public boolean checkFields() {
        List<String> warnings = new ArrayList<>();

        if (txtLogin.getText().isEmpty())
            warnings.add("Не заполнен логин");
        if (txtPassword.getText().isEmpty())
            warnings.add("Не заполнен пароль");

        if (0 < warnings.size())
            dialogs.warning(String.join("\n", warnings));

        return warnings.size() == 0;
    }
}
