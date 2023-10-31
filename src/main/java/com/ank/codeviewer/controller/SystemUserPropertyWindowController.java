package com.ank.codeviewer.controller;

import com.ank.codeviewer.model.Model;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.model.SystemUser;
import com.ank.codeviewer.model.enums.TypeSystemUser;
import com.ank.codeviewer.service.SystemUserService;
import com.ank.codeviewer.view.Dialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Форма регистрации, просмотра и изменения информации о системном пользователе
 */
@Setter
public class SystemUserPropertyWindowController extends BaseWindowController{
    /**
     * Режим работы формы
     */
    public enum Mode{REGISTRATION, INFO};
    private Mode mode;
    @FXML public TextField txtLogin;
    @FXML public TextField txtPassword;
    @FXML public TextField txtName;
    @FXML public TextField txtEmail;
    @FXML public Label lblTitle;
    @FXML public GridPane gpControls;
    private Dialogs dialogs;

    private SystemUserService systemUserService;

    public void onOKButtonClick(ActionEvent actionEvent) {
        if (mode == Mode.REGISTRATION){
            registratioNewSystemUser();
        } else {
            close();
        }
    }

    private void registratioNewSystemUser(){
        if (!checkFields())
            return;

        SystemUser systemUser = SystemUser.builder()
                .login(txtLogin.getText())
                .password(txtPassword.getText())
                .name(txtName.getText())
                .email(txtEmail.getText())
                .build();
        systemUser.setTypeUser(TypeSystemUser.USER);

        ResponseService<SystemUser> response = systemUserService.registratioNewSystemUser(systemUser);

        if (response.isOk())
            close();
        else
            Model.getInstance().getDialogs().error(response.getErrorMessage());
    }
    @Override
    public void show() {
        super.show();
        if (mode == Mode.REGISTRATION){
            lblTitle.setText("Регистрация");
        } else if (mode == Mode.INFO) {
            lblTitle.setText("Информация");
            fillDataSystemUser();
            gpControls.setDisable(true);
        }else {
            throw new RuntimeException("Не установлен режим работы формы 'Свойства системного пользователя'");
        }
    }

    private void fillDataSystemUser() {
        SystemUser user = Model.getInstance().getSystemUser();
        txtLogin.setText(user.getLogin());
        txtPassword.setText(user.getPassword());
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
    }

    public boolean checkFields() {
        List<String> warnings = new ArrayList<>();

        if (txtLogin.getText().isEmpty())
            warnings.add("Не заполнен логин");
        if (txtPassword.getText().isEmpty())
            warnings.add("Не заполнен пароль");
        if (txtName.getText().isEmpty())
            warnings.add("Не заполнено ФИО");
        if (txtEmail.getText().isEmpty())
            warnings.add("Не заполнен email");
        if (0 < warnings.size())
            dialogs.warning(String.join("\n", warnings));

        return warnings.size() == 0;
    }
}
