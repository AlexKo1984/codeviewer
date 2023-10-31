package com.ank.codeviewer.dto;

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
public class CommentPostDto {
    private int id;
    private String text;
    private String userLogin;
    private int userId;
    private int postId;
    private LocalDateTime dateCreate;
}
