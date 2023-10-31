package com.ank.codeviewer.action.viewpost;

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
public class ViewPostWindowEnableControlAction {
    private HBox hboxGradePost;
    private Button btnAddComment;
    /**
     * Делаем видимость элементов по ролям пользователя
     */
    public void refreshEnableControlOnRoleSystemUser(){
        SystemUser systemUser = Model.getInstance().getSystemUser();
        boolean userRole = systemUser.getTypeUser() != TypeSystemUser.UNREGISTERED_USER;

        hboxGradePost.setVisible(userRole);
        btnAddComment.setVisible(userRole);
    }
}
