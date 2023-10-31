package com.ank.codeviewer.sender;

import lombok.Getter;
import lombok.Setter;

/**
 * Структура ответа от HTTP сервера
 */
@Getter
@Setter
public class ResponseStructure<T> {
    private boolean ok;
    private int statusCode;
    private String errorMessage;
    private T dtoObject;
}
