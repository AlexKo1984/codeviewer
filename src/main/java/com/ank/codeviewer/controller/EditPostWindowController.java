package com.ank.codeviewer.controller;

import com.ank.codeviewer.action.editpost.EditPostWindowAction;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.model.post.Post;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Контроллер создания/редактирования поста
 */
@Getter
@Setter
@RequiredArgsConstructor
public class EditPostWindowController extends BaseWindowController {
    @FXML public Label lblTitleForm;
    @FXML public Label lblAuthor;
    @FXML public Label lblDate;
    @FXML public Label lblLangCode;
    @FXML public TextArea txtCode;
    @FXML public TextArea txtDescription;
    @FXML public TextField txtTitle;
    @FXML public HBox hboxPageNavigator;

    private EditPostWindowAction action;

    public void setAction(EditPostWindowAction action) {
        this.action = action;
        action.setTxtCode(txtCode);
        action.setTxtDescription(txtDescription);
        action.setTxtTitle(txtTitle);
        action.setLblAuthor(lblAuthor);
        action.setLblLangCode(lblLangCode);
        action.setLblDate(lblDate);
        action.setLblTitleForm(lblTitleForm);
    }

    public void onOKButtonClck(ActionEvent actionEvent) {
        if (action.checkFields()){
            ResponseService<Post> response = action.savePost();

            if (response.isOk()){
                close();
            }
        }
    }
}
