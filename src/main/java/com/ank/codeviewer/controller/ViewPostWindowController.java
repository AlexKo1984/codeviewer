package com.ank.codeviewer.controller;

import com.ank.codeviewer.action.viewpost.ViewPostWindowAction;
import com.ank.codeviewer.action.viewpost.ViewPostWindowCommentAction;
import com.ank.codeviewer.action.viewpost.ViewPostWindowEnableControlAction;
import com.ank.codeviewer.action.viewpost.ViewPostWindowGradeAction;
import com.ank.codeviewer.controller.frame.PageNavigatorController;
import com.ank.codeviewer.model.CommentPost;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

/**
 * Контроллер поста
 */
@Getter
@Setter
public class ViewPostWindowController extends BaseWindowController{
    @FXML public ListView<CommentPost> lstComments;
    @FXML public TextArea txtCode;
    @FXML public TextArea txtDescription;
    @FXML public TextField txtTitle;
    @FXML public HBox hboxPageNavigator;
    @FXML public Label lblAuthor;
    @FXML public Label lblLangCode;
    @FXML public Label lblDate;
    @FXML public HBox hboxGradePost;
    @FXML public HBox hboxTableButtons;
    @FXML public Label lblAvgGrade;
    @FXML public Label lblYourGradePost;
    @FXML public Button btnSetGradePost;
    @FXML public ComboBox<Integer> cbGradePost;
    @FXML public Button btnAddComment;
    private ViewPostWindowAction action;
    private ViewPostWindowGradeAction gradeAction;
    private ViewPostWindowCommentAction commentAction;
    private ViewPostWindowEnableControlAction actionEnableControl;
    private PageNavigatorController pageNavigatorController;
    /**
     * Признак, что нужно обновить комментарии
     */
    private boolean needRefreshComments = true;
    public void onRefreshCommentsButtonClick(ActionEvent actionEvent) {
        commentAction.refreshComments();
    }

    public void onAddCommentsButtonClick(ActionEvent actionEvent) {
        commentAction.addComment();
    }

    public void onDeleteCommentsButtonClick(ActionEvent actionEvent) {
    }

    public void onSetGradePostButtonClick(ActionEvent actionEvent) {
        gradeAction.gradeThisPost();
    }

    public void setAction(ViewPostWindowAction action) {
        this.action = action;

        action.setTxtCode(txtCode);
        action.setTxtDescription(txtDescription);
        action.setTxtTitle(txtTitle);
        action.setLblAuthor(lblAuthor);
        action.setLblLangCode(lblLangCode);
        action.setLblDate(lblDate);
    }

    public void setGradeAction(ViewPostWindowGradeAction gradeAction) {
        this.gradeAction = gradeAction;
        gradeAction.setLblAvgGrade(lblAvgGrade);
        gradeAction.setLblYourGradePost(lblYourGradePost);
        gradeAction.setCbGradePost(cbGradePost);
        gradeAction.setBtnSetGradePost(btnSetGradePost);
    }

    public void setCommentAction(ViewPostWindowCommentAction commentAction) {
        this.commentAction = commentAction;
        commentAction.setLstComments(lstComments);
        commentAction.setOwner(getStage());
        commentAction.setPageNavigatorController(pageNavigatorController);
    }

    @Override
    public void onShow() {
        super.onShow();
        getPageNavigatorController().currentPageProperty().addListener(
                (obs, oldProperty, newProperty) -> commentAction.refreshComments());

        gradeAction.refreshValueGradePost();
        actionEnableControl.refreshEnableControlOnRoleSystemUser();
    }

    @FXML public void onSelectionChangedTabComment(Event event) {
        //Событте происходит только при переключении вкладки
        //Первое переключение может быть только на комменты, это устраивает
        if (needRefreshComments) {
            needRefreshComments = false;
            commentAction.refreshComments();
        }
    }

    public void setActionEnableControl(ViewPostWindowEnableControlAction actionEnableControl) {
        this.actionEnableControl = actionEnableControl;
        actionEnableControl.setHboxGradePost(hboxGradePost);
        actionEnableControl.setBtnAddComment(btnAddComment);
    }
}
