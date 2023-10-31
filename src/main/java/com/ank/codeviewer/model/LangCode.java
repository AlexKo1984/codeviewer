package com.ank.codeviewer.model;

import lombok.*;

/**
 * Язык кода для постов
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LangCode {
    private Integer id;
    private String name;
    private String title;

    @Override
    public String toString() {
        return title;
    }
}
