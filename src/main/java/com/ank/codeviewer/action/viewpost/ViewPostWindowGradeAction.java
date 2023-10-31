package com.ank.codeviewer.action.viewpost;

import com.ank.codeviewer.model.GradePost;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.service.GradePostService;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Действия по просмотру и установки оценки поста текущем пользователем
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ViewPostWindowGradeAction {
    private final GradePostService gradePostService;
    private final int userId;
    private final int postId;
    private Label lblAvgGrade;
    private Label lblYourGradePost;
    private ComboBox<Integer> cbGradePost;
    private Button btnSetGradePost;

    public void refreshValueGradePost(){
        ResponseService<GradePost> responseGrade = gradePostService.findByUserIdAndPostId(userId, postId);

        boolean maybe = !responseGrade.isOk();

        cbGradePost.setVisible(maybe);
        btnSetGradePost.setVisible(maybe);
        lblYourGradePost.setVisible(!maybe);

        if (!maybe){
            lblYourGradePost.setText(String.valueOf(responseGrade.getObject().getValue()));
        }
        if (cbGradePost.getItems().isEmpty()){
            fillListGrades();
            cbGradePost.setValue(1);
        }


    }

    private void fillListGrades() {
        for (int i = 1; i <= 5; i++)
            cbGradePost.getItems().add(i);
    }
    public void gradeThisPost(){
        ResponseService<GradePost> responseGrade = gradePostService.createGradePost(userId, postId, cbGradePost.getValue());
        refreshValueGradePost();
    }
    public void setAvgGradePost(double grade){
        if (grade == 0){
            lblAvgGrade.setText("Нет");
        }else {
            lblAvgGrade.setText(String.valueOf(grade));
        }
    }
}
