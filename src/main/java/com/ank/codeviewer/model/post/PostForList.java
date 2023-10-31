package com.ank.codeviewer.model.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Пост для просмотра в списке
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostForList {
    private Integer id;
    private String title;
    private LocalDateTime dateChange;
    private String userLogin;
    private Integer userId;
    private Double grade;
}
