package com.ank.codeviewer.dto.post;

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
public class PostForListDto {
    private Integer id;
    private String title;
    private LocalDateTime dateChange;
    private String userLogin;
    private Integer userId;
    private Double grade;
}
