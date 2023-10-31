package com.ank.codeviewer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Для тестирования возможности подключения
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestConnectDto {
    private String message;
    private LocalDateTime date;
}
