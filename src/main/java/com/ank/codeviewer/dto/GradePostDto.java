package com.ank.codeviewer.dto;

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
public class GradePostDto {
    private Integer userId;
    private Integer postId;
    private Integer value;//Значение оценки
}
