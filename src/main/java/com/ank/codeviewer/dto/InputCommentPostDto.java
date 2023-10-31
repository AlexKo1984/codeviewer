package com.ank.codeviewer.dto;

import lombok.*;

/**
 * Данные при создании комментария
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputCommentPostDto {
    private String text;
    private Integer userId;
    private Integer postId;
}
