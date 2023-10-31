package com.ank.codeviewer.action.viewpost;

import com.ank.codeviewer.mapper.UserMapper;
import com.ank.codeviewer.model.LangCode;
import com.ank.codeviewer.model.User;
import com.ank.codeviewer.model.post.Post;
import com.ank.codeviewer.service.PostService;
import com.ank.codeviewer.util.DateFormate;
import com.ank.codeviewer.view.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Управление контроллером поста. Контент поста, без комментов и оценок
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ViewPostWindowAction {
    private int pageSize = 10;

    public TextArea txtCode;
    public TextArea txtDescription;
    public TextField txtTitle;
    private Label lblAuthor;
    private Label lblDate;
    private Label lblLangCode;
    //private Label avgGrade;

    public void fillContentFromPost(Post post) {
        lblAuthor.setText(post.getUser().getLogin());
        lblDate.setText(DateFormate.formatDatePost(post.getDateChange()));
        lblLangCode.setText(String.valueOf(post.getLangCode()));

        txtTitle.setText(post.getTitle());
        txtCode.setText(post.getCode());
        txtDescription.setText(post.getDescription());
        //avgGrade.setText(String.valueOf(post.getAvgGrade()));
    }
}
