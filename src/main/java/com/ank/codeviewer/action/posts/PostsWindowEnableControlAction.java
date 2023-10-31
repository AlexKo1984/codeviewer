package com.ank.codeviewer.action.posts;

import com.ank.codeviewer.model.Model;
import com.ank.codeviewer.model.SystemUser;
import com.ank.codeviewer.model.enums.TypeSystemUser;
import com.ank.codeviewer.model.post.PostForList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Разрешает или запрещает доступ к элементам формы
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PostsWindowEnableControlAction {
    private TableView<PostForList> tvTablePosts;
    private HBox hboxAutenthicationUser;
    private HBox hboxNavigation;
    private HBox hboxSubString;
    private Button btnEditPost;
    private Button btnDeletePost;
    private Button btnAddPost;

    public void refreshEnableControl(){
        boolean disableAll = Model.getInstance().getServerAddress().isEmpty();

        tvTablePosts.setDisable(disableAll);
        hboxAutenthicationUser.setDisable(disableAll);
        hboxNavigation.setDisable(disableAll);
        hboxSubString.setDisable(disableAll);

        refreshEnableControlOnRoleSystemUser();
    }

    /**
     * Делаем видимость элементов по ролям пользователя
     */
    public void refreshEnableControlOnRoleSystemUser(){
        SystemUser systemUser = Model.getInstance().getSystemUser();
        boolean userRole = systemUser.getTypeUser() != TypeSystemUser.UNREGISTERED_USER;
        boolean adminRole = systemUser.getTypeUser() == TypeSystemUser.ADMIN;

        btnAddPost.setVisible(userRole);
        btnEditPost.setVisible(userRole);
        btnDeletePost.setVisible(adminRole);
    }

}
