package com.ank.codeviewer.sender.enums;

public enum RequestToServer {
    GET_USER_ID,
    CREATE_POST,
    /**
     * Получаем страницу с постами
     */
    GET_PAGE_POSTS,
    /**
     * Тестирование подключения
     */
    GET_TEST_CONNECT, GET_ALL_LANGCODE, CREATE_USER, AUTHENTICATION, FIND_POST, UPDATE_POST, FIND_GRADE, CREATE_GRADE_POST, CREATE_COMMENT_POST, GET_PAGE_COMMENTS_POST, DELETE_POST,
}
