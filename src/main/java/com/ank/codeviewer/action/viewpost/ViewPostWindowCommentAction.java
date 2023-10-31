package com.ank.codeviewer.action.viewpost;

import com.ank.codeviewer.clientservice.ClientCommentService;
import com.ank.codeviewer.controller.frame.PageNavigatorController;
import com.ank.codeviewer.model.CommentPost;
import com.ank.codeviewer.model.Page;
import com.ank.codeviewer.model.post.Post;
import com.ank.codeviewer.model.post.PostForList;
import com.ank.codeviewer.service.CommentPostService;
import com.ank.codeviewer.service.PostService;
import com.ank.codeviewer.util.DateFormate;
import com.ank.codeviewer.view.Dialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Управление контроллером поста
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ViewPostWindowCommentAction {
    private final ClientCommentService clientCommentService;
    private final CommentPostService commentPostService;
    private final Dialogs dialogs;
    private final int userId;
    private final int postId;
    private Window owner;
    private PageNavigatorController pageNavigatorController;
    private int pageSize = 10;

    public ListView<CommentPost> lstComments;

    public void refreshComments() {
        lstComments.getItems().clear();

        //Нет выбранной страницы
        if (getPageNavigatorController().getCurrentPage() == -1)
            return;

        Page<CommentPost> page = commentPostService.getPageCommentPost(
                postId,
                getPageNavigatorController().getCurrentPage(),
                pageSize);

        lstComments.getItems().addAll(page.getContent());

        getPageNavigatorController().setTotalPage(page.getTotalPage());
        getPageNavigatorController().setCurrentPage(page.getPageNumber());
    }
    public void addComment() {
        clientCommentService.addComment(owner, userId, postId);
    }
}
