package com.ank.codeviewer.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;

import java.util.Optional;

/**
 * Диалоги, сообщения, ошибки
 */
@AllArgsConstructor
public class Dialogs {
    private String title;
    /**
     * Диалог вопроса Да/Нет
     * @param header - текст вопроса
     * @param content - содержание/дополнительная информация вопроса
     * @return
     */
    public boolean questionYesNo(String header, String content) {
        ButtonType yes = new ButtonType("Да", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("Нет", ButtonBar.ButtonData.NO);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, yes, no);

        alert.setTitle(title);
        alert.setHeaderText(header);
        Optional<ButtonType> answer = alert.showAndWait();

        Stage st = new Stage();

        return answer.orElse(no) == yes;
    }
    /**
     * Показ сообщения
     * @param text текст сообщения
     */
    public void message(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText("Сообщение");
        alert.setContentText(text);

        alert.showAndWait();
    }
    /**
     * Окно предупреждения
     * @param text
     */
    public void error(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Ошибка");
        alert.setContentText(text);
        alert.showAndWait();
    }
    /**
     * Окно предупреждения
     * @param text
     */
    public void warning(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText("Внимание");
        alert.setContentText(text);
        alert.showAndWait();
    }
}
