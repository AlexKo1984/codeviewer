package com.ank.codeviewer.controller;

import com.ank.codeviewer.clientservice.ClientCommentService;
import com.ank.codeviewer.model.CommentPost;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.service.CommentPostService;
import com.ank.codeviewer.view.Dialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCommentPostWindowController extends BaseWindowController{
    private CommentPostService service;
    private Dialogs dialogs;
    @FXML public TextArea txtText;
    private int userId;
    private int postId;

    public void onOkButtonClick(ActionEvent actionEvent) {
        if (checkFields()){
            ResponseService<CommentPost> response =  service.addCommentPost(txtText.getText(), userId, postId);
            if (response.isOk())
                close();
            else
                dialogs.error(response.getErrorMessage());
        }
    }

    public boolean checkFields() {
        boolean result = !txtText.getText().isEmpty();
        if (!result){
            dialogs.warning("Не заполнен комментарий");
        }

        return result;
    }
}
