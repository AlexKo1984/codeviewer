package com.ank.codeviewer.clientservice;

import com.ank.codeviewer.action.editpost.EditPostWindowAction;
import com.ank.codeviewer.action.posts.PostWindowAuthenticationSystemUser;
import com.ank.codeviewer.action.posts.PostsWindowAction;
import com.ank.codeviewer.action.posts.PostsWindowEnableControlAction;
import com.ank.codeviewer.action.viewpost.ViewPostWindowAction;
import com.ank.codeviewer.action.viewpost.ViewPostWindowCommentAction;
import com.ank.codeviewer.action.viewpost.ViewPostWindowEnableControlAction;
import com.ank.codeviewer.action.viewpost.ViewPostWindowGradeAction;
import com.ank.codeviewer.controller.EditPostWindowController;
import com.ank.codeviewer.controller.ViewPostWindowController;
import com.ank.codeviewer.controller.PostsWindowController;
import com.ank.codeviewer.model.*;
import com.ank.codeviewer.model.post.Post;
import com.ank.codeviewer.view.Dialogs;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class ClientPostService {
    public void showPosts(){
        Model model = Model.getInstance();

        PostsWindowController controller = model.getViewFactory().getMainWindowController();
        PostsWindowAction action = new PostsWindowAction(model.getPostService(), model.getLandCodeService(), model.getClientPostService(), model.getDialogs());
        PostWindowAuthenticationSystemUser actionAuthentication = new PostWindowAuthenticationSystemUser(model.getClientSystemUserService(), controller.getStage());
        PostsWindowEnableControlAction enableControlAction = new PostsWindowEnableControlAction();
        controller.setActionAuthentication(actionAuthentication);
        controller.setAction(action);
        controller.setActionEnableControl(enableControlAction);

        model.systemUserProperty().addListener(
                (obs, oldProperty, newProperty)-> controller.refreshStatusSystemUser());

        model.getSystemUserService().setSystemUser(SystemUser.getUnregisteredUser());

        controller.show();
    }

    /**
     * Показать пост
     */
    public void showPost(Window owner, int postId) {
        Model model = Model.getInstance();
        ResponseService<Post> response = model.getPostService().findPost(postId);

        if (response.isOk()){
            int userId = model.getSystemUser().getId();
            ViewPostWindowController controller = model.getViewFactory().getPostWindowController(owner);
            ViewPostWindowAction action = new ViewPostWindowAction();
            ViewPostWindowGradeAction gradeAction = new ViewPostWindowGradeAction(model.getGradePostService(), userId, postId);
            ViewPostWindowCommentAction commentAction = new ViewPostWindowCommentAction(model.getClientCommentService(), model.getCommentPostService(), model.getDialogs(), userId, postId);
            ViewPostWindowEnableControlAction actionEnableControl = new ViewPostWindowEnableControlAction();

            controller.setActionEnableControl(actionEnableControl);
            controller.setAction(action);
            controller.setGradeAction(gradeAction);
            controller.setCommentAction(commentAction);
            action.fillContentFromPost(response.getObject());
            gradeAction.setAvgGradePost(response.getObject().getAvgGrade());
            controller.show();
        }else {
            model.getDialogs().error(response.getErrorMessage());
        }
    }
    public void addPost(Window owner, LangCode langCode) {
        Model model = Model.getInstance();

        EditPostWindowController controller = model.getViewFactory().getEditPostWindowController(owner);
        EditPostWindowAction action = new EditPostWindowAction(model.getPostService(), model.getUserMapper(), model.getDialogs());
        controller.setAction(action);
        action.setLangCode(langCode);
        action.setMode(EditPostWindowAction.Mode.ADD_POST);
        controller.show();
    }

    public void editPost(Stage stage, int postId) {
        Model model = Model.getInstance();
        ResponseService<Post> response = model.getPostService().findPost(postId);

        if (response.isOk()){
            Post post = response.getObject();
            EditPostWindowController controller = model.getViewFactory().getEditPostWindowController(stage);
            EditPostWindowAction action = new EditPostWindowAction(model.getPostService(), model.getUserMapper(), model.getDialogs());
            controller.setAction(action);
            action.setMode(EditPostWindowAction.Mode.EDIT_POST);
            action.fillContentFromPost(post);
            controller.show();
        }else {
            model.getDialogs().error(response.getErrorMessage());
        }
    }

    public void deletePost(Stage stage, String titleForQuestion, int postId) {
        Model model = Model.getInstance();

        if (model.getDialogs().questionYesNo(titleForQuestion, "Удалить пост?")){
            ResponseService<Object> response = model.getPostService().deletePost(postId);

            if (!response.isOk()){
                model.getDialogs().error(response.getErrorMessage());
            }
        }
    }
}
