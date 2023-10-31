package com.ank.codeviewer.action.editpost;

import com.ank.codeviewer.mapper.UserMapper;
import com.ank.codeviewer.model.LangCode;
import com.ank.codeviewer.model.Model;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.model.User;
import com.ank.codeviewer.model.post.Post;
import com.ank.codeviewer.service.PostService;
import com.ank.codeviewer.util.DateFormate;
import com.ank.codeviewer.view.Dialogs;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Управление контроллером поста
 */
@Getter
@Setter
@RequiredArgsConstructor
public class EditPostWindowAction {
    public enum Mode{ADD_POST, EDIT_POST}
    private Mode mode;
    private final PostService postService;
    private LangCode langCode;
    private final UserMapper userMapper;
    private Label lblTitleForm;
    private Label lblAuthor;
    private Label lblDate;
    private Label lblLangCode;
    private Integer postId;
    private final Dialogs dialogs;
    public TextArea txtCode;
    public TextArea txtDescription;
    public TextField txtTitle;

    /**
     * Сохранение поста. Создает или обновляет
     * @return
     */
    public ResponseService<Post> savePost() {
        if (mode == Mode.ADD_POST)
            return addPost();
        else
            return updatePost();
    }

    /**
     * Добавление нового поста
     * @return
     */
    private ResponseService<Post> addPost() {
        ResponseService<Post> response = postService.addPost(buildPostFromContent());

        if (!response.isOk()) {
            dialogs.error(response.getErrorMessage());
        }
        return response;
    }

    /**
     * Обновляет существующий пост
     * @return
     */
    private ResponseService<Post> updatePost() {
        ResponseService<Post> response = postService.updatePost(buildPostFromContent());

        if (!response.isOk()) {
            dialogs.error(response.getErrorMessage());
        }
        return response;
    }
    private Post buildPostFromContent(){
        return Post.builder()
                .id(postId)
                .title(txtTitle.getText())
                .code(txtCode.getText())
                .description(txtDescription.getText())
                .user(getUser())
                .langCode(langCode)
                .build();
    }
    private User getUser() {
        return userMapper.mapToUser(Model.getInstance().getSystemUser());
    }

    public boolean checkFields() {
        List<String> warnings = new ArrayList<>();

        if (txtTitle.getText().isEmpty())
            warnings.add("Не заполнен заголовок");
        if (txtDescription.getText().isEmpty())
            warnings.add("Не заполнено описание");
        if (txtCode.getText().isEmpty())
            warnings.add("Не заполнен код");

        if (0 < warnings.size())
            dialogs.warning(String.join("\n", warnings));

        return warnings.size() == 0;
    }

    public void setMode(Mode mode) {
        if (mode == Mode.ADD_POST){
            lblTitleForm.setText("Новый пост");
            lblAuthor.setText(getUser().getLogin());
            lblDate.setText(String.valueOf(""));
        }
        else{
            lblTitleForm.setText("Редактирование поста");
        }

        this.mode = mode;
    }

    public void fillContentFromPost(Post post) {
        lblAuthor.setText(post.getUser().getLogin());
        lblDate.setText(DateFormate.formatDatePost(post.getDateChange()));

        txtTitle.setText(post.getTitle());
        txtCode.setText(post.getCode());
        txtDescription.setText(post.getDescription());

        setLangCode(post.getLangCode());
        postId = post.getId();
    }

    public void setLangCode(LangCode langCode) {
        lblLangCode.setText(langCode.getName());
        this.langCode = langCode;
    }
}
