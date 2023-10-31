package com.ank.codeviewer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Ответ при работе сервиса
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseService <T>{
    private boolean ok;
    private String errorMessage;
    private T object;
}
