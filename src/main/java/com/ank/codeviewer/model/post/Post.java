package com.ank.codeviewer.model.post;

import com.ank.codeviewer.model.LangCode;
import com.ank.codeviewer.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Пост для просмотра в списке
 */
@Data
@Builder
public class Post {
    private Integer id;
    private String code;
    private String title;
    private String description;
    private LocalDateTime dateCreate;
    private LocalDateTime dateChange;
    private User user;
    private LangCode langCode;
    /**
     * Средняя оценка поста
     */
    private double avgGrade;
}
