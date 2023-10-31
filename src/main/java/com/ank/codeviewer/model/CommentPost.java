package com.ank.codeviewer.model;

import com.ank.codeviewer.util.DateFormate;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Данные о посте
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentPost {
    private int id;
    private String text;
    private String userLogin;
    private int userId;
    private int postId;
    private LocalDateTime dateCreate;

    @Override
    public String toString() {
        return "Автор: " + userLogin + "   Дата: " + DateFormate.formatDatePost(dateCreate) + "\n" + text + "\n";
    }
}
