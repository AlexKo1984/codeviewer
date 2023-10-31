package com.ank.codeviewer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Объект для постраничного вывода
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Page<T>{
    private long total;//всего элементов
    private List<T> content;//Элементы
    private int pageNumber;//номер страницы
    private int pageSize;//размер страницы
    private int totalPage;//всего страниц
}
