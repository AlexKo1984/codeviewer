package com.ank.codeviewer.dto.post;

import java.time.LocalDateTime;

import com.ank.codeviewer.dto.ShortUserDto;
import com.ank.codeviewer.dto.langcode.LangCodeDto;
import com.ank.codeviewer.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Integer id;
    private String code;
    private String title;
    private String description;
    @JsonIgnoreProperties
    private LocalDateTime dateChange;
    @JsonIgnoreProperties
    private LocalDateTime dateCreate;
    private ShortUserDto owner;
    private LangCodeDto langCode;
    /**
     * Средняя оценка поста
     */
    private double avgGrade;
}
