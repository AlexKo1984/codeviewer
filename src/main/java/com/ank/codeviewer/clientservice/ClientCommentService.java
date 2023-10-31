package com.ank.codeviewer.clientservice;

import com.ank.codeviewer.controller.AddCommentPostWindowController;
import com.ank.codeviewer.controller.AuthenticationSystemUserWindowController;
import com.ank.codeviewer.controller.SystemUserPropertyWindowController;
import com.ank.codeviewer.model.CommentPost;
import com.ank.codeviewer.model.Model;
import com.ank.codeviewer.model.Page;
import javafx.stage.Window;
import lombok.Getter;
import lombok.Setter;

/**
 * Формы для регистрации, входа и выхода пользователя
 */
@Getter
@Setter
public class ClientCommentService {
    public void addComment(Window owner, int userId, int postId) {
        Model model = Model.getInstance();
        AddCommentPostWindowController controller = Model.getInstance().getViewFactory().getAddCommentPostWindowController(owner);
        controller.setService(model.getCommentPostService());
        controller.setDialogs(model.getDialogs());
        controller.setUserId(userId);
        controller.setPostId(postId);
        controller.show();
    }
}
