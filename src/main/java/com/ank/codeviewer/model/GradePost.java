package com.ank.codeviewer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Данные об оценке поста
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradePost {
    private Integer userId;
    private Integer postId;
    private Integer value;//Значение оценки
}
