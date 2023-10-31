package com.ank.codeviewer.dto.post;

import lombok.*;

/**
 * Данные для создания/обновления поста
 */
@Getter
@Setter
@Builder
public class InputPostDto {
    private String code;
    private String title;
    private String description;
    private Integer userId;
    private Integer langCodeId;
}
