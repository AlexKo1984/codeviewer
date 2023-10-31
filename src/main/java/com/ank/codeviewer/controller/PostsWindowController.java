package com.ank.codeviewer.controller;

import com.ank.codeviewer.action.posts.PostWindowAuthenticationSystemUser;
import com.ank.codeviewer.action.posts.PostsWindowAction;
import com.ank.codeviewer.action.posts.PostsWindowEnableControlAction;
import com.ank.codeviewer.controller.frame.PageNavigatorController;
import com.ank.codeviewer.model.LangCode;
import com.ank.codeviewer.model.post.PostForList;
import com.ank.codeviewer.util.DateFormate;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@Getter
@Setter
public class PostsWindowController extends BaseWindowController implements Initializable {
    @FXML public ComboBox<LangCode> cbCodeLang;
    @FXML public HBox hboxPageNavigator;
    @FXML public TextField txtServerAddress;
    @FXML public TableColumn<PostForList, String> columnAuthor;
    @FXML public TableColumn<PostForList, String> columnTitle;
    @FXML public TableColumn<PostForList, LocalDateTime> columnDateChange;
    @FXML public TableColumn<PostForList, Double> columnGrade;
    @FXML public TableView<PostForList> tvTablePosts;
    @FXML public Button btnSystemUser;
    @FXML public Button btnActionSystemUser;
    @FXML public HBox hboxAutenthicationUser;
    @FXML public HBox hboxNavigation;
    @FXML public Button btnEditPost;
    @FXML public Button btnDeletePost;
    @FXML public Button btnAddPost;
    @FXML public TextField txtFilterCode;
    @FXML public HBox hboxSubString;
    private PageNavigatorController pageNavigatorController;
    private PostsWindowAction action;
    private PostsWindowEnableControlAction actionEnableControl;
    private PostWindowAuthenticationSystemUser actionAuthentication;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnAuthor.setCellValueFactory(new PropertyValueFactory<PostForList, String>("userLogin"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<PostForList, String>("title"));
        columnDateChange.setCellValueFactory(new PropertyValueFactory<PostForList, LocalDateTime>("dateChange"));
        columnGrade.setCellValueFactory(new PropertyValueFactory<PostForList, Double>("grade"));

        DateTimeFormatter formatter = DateFormate.formatter;
//Показываем дату в формате
        columnDateChange.setCellFactory(myDateTableCell -> {
            return new TableCell<PostForList, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime date, boolean dateIsEmpty) {
                    super.updateItem(date, dateIsEmpty);
                    if (date == null || dateIsEmpty) {
                        setText(null);
                    } else {
                        setText(formatter.format(date));
                    }
                }
            };
        });
        //Цветовое выделение строки в зависимости от оценки поста
        tvTablePosts.setRowFactory(tv -> new TableRow<PostForList>() {
            @Override
            protected void updateItem(PostForList item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || item.getGrade() == 0.0)
                    setStyle("");
                else if (3.5 < item.getGrade() && item.getGrade() <= 4.5)
                    setStyle("-fx-background-color: lightyellow;");
                else if (4.5 < item.getGrade())
                    setStyle("-fx-background-color: lightgreen");
                else
                    setStyle("");
            }
        });
        //Формат ячейки оценки, если нет то "Нет оценки"
        columnGrade.setCellFactory(myTableCell -> {
            return new TableCell<PostForList, Double>() {
                @Override
                protected void updateItem(Double grade, boolean empty) {
                    super.updateItem(grade, empty);
  //Показ текста
                    if (!empty && grade == 0.0)
                        setText("Нет оценки");
                    else if (!empty && 0.0 < grade)
                        setText(String.valueOf(grade));
                    else
                        setText("");
                }
            };
        });
        //Колонки по всей ширине
        tvTablePosts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        cbCodeLang.valueProperty().addListener(this::onChangeCodeLang);
    }

    /**
     * Обновился язык постов
     * @param observable
     */
    private void onChangeCodeLang(Observable observable) {
        action.refreshPosts();
    }

    @FXML
    public void onRefreshPostsButtonClick(ActionEvent actionEvent) {
        action.refreshPosts();
    }

    @Override
    public void onShow() {
        super.onShow();
        //Подписка на изменение страницы пота
        getPageNavigatorController().currentPageProperty().addListener(
                (obs, oldProperty, newProperty) -> action.refreshPosts());

        actionEnableControl.refreshEnableControl();
    }

    @FXML public void onRefreshContentButtonClick(ActionEvent actionEvent) {
        action.handleNewServerAdress();
        actionAuthentication.clearCurrentAuthentication();
        actionEnableControl.refreshEnableControl();
        //Для отладки.
        //Model.getInstance().getSystemUserService().authenticationSystemUser("Pavlova", "123");
    }

    public void setAction(PostsWindowAction action) {
        this.action = action;

        action.setTvTablePosts(tvTablePosts);
        action.setCbCodeLang(cbCodeLang);
        action.setTxtServerAddress(txtServerAddress);
        action.setPageNavigatorController(pageNavigatorController);
        action.setTxtFilterCode(txtFilterCode);
        action.setStage(getStage());
    }

    public void setActionEnableControl(PostsWindowEnableControlAction actionEnableControl) {
        this.actionEnableControl = actionEnableControl;

        actionEnableControl.setTvTablePosts(tvTablePosts);
        actionEnableControl.setHboxNavigation(hboxNavigation);
        actionEnableControl.setHboxAutenthicationUser(hboxAutenthicationUser);
        actionEnableControl.setHboxSubString(hboxSubString);
        actionEnableControl.setBtnAddPost(btnAddPost);
        actionEnableControl.setBtnEditPost(btnEditPost);
        actionEnableControl.setBtnDeletePost(btnDeletePost);
    }

    public void setActionAuthentication(PostWindowAuthenticationSystemUser actionAuthentication) {
        this.actionAuthentication = actionAuthentication;

        actionAuthentication.setBtnActionSystemUser(btnActionSystemUser);
        actionAuthentication.setBtnSystemUser(btnSystemUser);
    }

    @FXML
    public void onSystemUserButtonClick(ActionEvent actionEvent) {
        actionAuthentication.handleSystemUser();
    }

    /**
     * Действия по входу и выходу пользователя
     * @param actionEvent
     */
    @FXML public void onActionSystemUserButtonClick(ActionEvent actionEvent) {
        actionAuthentication.enterExitSystemUser();
    }

    /**
     * Обновление статуса системного пользователя
     * Подключается в systemUserProperty().addListener
     */
    @FXML public void refreshStatusSystemUser() {
        actionAuthentication.refresh();
        actionEnableControl.refreshEnableControlOnRoleSystemUser();
    }

    @FXML public void onAddPostButtonClick(ActionEvent actionEvent) {
        action.AddPost();
    }

    @FXML public void onEditPostButtonClick(ActionEvent actionEvent) {
        action.editPost();
    }

    @FXML public void onDeletePostButtonClick(ActionEvent actionEvent) {
        action.deletePost();
    }

    @FXML public void onTableViewKeyPressed(KeyEvent keyEvent) {
//Нажали ENTER
        if (keyEvent.getCode() == KeyCode.ENTER)
            onTableViewSelectRow();
    }

    @FXML public void onTableViewMousePressed(MouseEvent mouseEvent) {
//        Двойной клик
        if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2)
            onTableViewSelectRow();
    }

    /**
     * Событие при выборе строки таблицы. Либо нажали ENTER, ибо 2й клик мышью
     */
    public void onTableViewSelectRow(){
        action.showCurrentPost();
    }
}
