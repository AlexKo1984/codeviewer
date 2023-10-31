package com.ank.codeviewer.action.posts;

import com.ank.codeviewer.clientservice.ClientPostService;
import com.ank.codeviewer.controller.frame.PageNavigatorController;
import com.ank.codeviewer.model.LangCode;
import com.ank.codeviewer.model.Model;
import com.ank.codeviewer.model.Page;
import com.ank.codeviewer.model.SystemUser;
import com.ank.codeviewer.model.enums.TypeSystemUser;
import com.ank.codeviewer.model.post.PostForList;
import com.ank.codeviewer.service.LandCodeService;
import com.ank.codeviewer.service.PostService;
import com.ank.codeviewer.transport.SenderLandCodeService;
import com.ank.codeviewer.view.Dialogs;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PostsWindowAction {
    private final PostService postService;
    private final LandCodeService landCodeService;
    private final ClientPostService clientPostService;
    private final Dialogs dialogs;
    private ComboBox<LangCode> cbCodeLang;
    private TextField txtServerAddress;
    private TableView<PostForList> tvTablePosts;
    private Stage stage;
    private PageNavigatorController pageNavigatorController;
    private TextField txtFilterCode;
    private int pageSize = 10;

    /**
     * Обновился язык постов
     * @param observable
     */
    private void onChangeCodeLang(Observable observable) {
        refreshPosts();
    }

    public void refreshPosts() {
        tvTablePosts.getItems().clear();

        //Нет выбранной страницы
        if (getPageNavigatorController().getCurrentPage() == -1)
            return;


        Page<PostForList> page = postService.getPagePosts(
                getPageNavigatorController().getCurrentPage(),
                pageSize,
                cbCodeLang.getValue().getId(),
                txtFilterCode.getText());

        ObservableList<PostForList> items = FXCollections.<PostForList>observableArrayList(page.getContent());
        tvTablePosts.setItems(items);

        getPageNavigatorController().setTotalPage(page.getTotalPage());
        getPageNavigatorController().setCurrentPage(page.getPageNumber());
    }

    /**
     * Полное обновление содержимого
     */
    public void handleNewServerAdress(){
        refreshServerAddress();
        List<LangCode> langCodes = landCodeService.getAllLandCode();

        handleFillLangCode(langCodes);
    }
    public void handleFillLangCode(List<LangCode> langCodes) {
        cbCodeLang.getItems().clear();
        cbCodeLang.getItems().addAll(langCodes);

        if (!cbCodeLang.getItems().isEmpty()){
            cbCodeLang.setValue(cbCodeLang.getItems().get(0));
        }
    }
    public void refreshServerAddress() {
        Model.getInstance().refreshServerAddress(txtServerAddress.getText());
    }

    public void AddPost() {
        if (cbCodeLang.getValue() == null) {
            Model.getInstance().getDialogs().warning("Не выбран язык поста");
            return;
        }
        clientPostService.addPost(getStage(), cbCodeLang.getValue());
    }

    public void editPost() {
        PostForList post = getSelectedPost();

        if (post != null && isAllowedEditPost(post)){
            clientPostService.editPost(stage, post.getId());
        }
    }

    /**
     * Разрешено редактировать пост
     * @param post - пост
     * @return
     */
    private boolean isAllowedEditPost(PostForList post) {
        SystemUser systemUser = Model.getInstance().getSystemUser();
        boolean adminRole = systemUser.getTypeUser() == TypeSystemUser.ADMIN;

        boolean isAllowed = adminRole || post.getUserId() == systemUser.getId();

        if (!isAllowed){
            dialogs.warning("Нет прав на редактирование поста");
        }

        return isAllowed;
    }

    public void deletePost() {
        PostForList post = getSelectedPost();

        if (post != null && isAllowedEditPost(post)){
            clientPostService.deletePost(stage, post.getTitle(), post.getId());
        }
    }

    private PostForList getSelectedPost(){
        return tvTablePosts.getSelectionModel().getSelectedItem();
    }

    public void showCurrentPost() {
        PostForList post = getSelectedPost();

        if (post != null){
            clientPostService.showPost(stage, post.getId());
        }
    }
}
