package com.ank.codeviewer.sender;

import com.ank.codeviewer.sender.enums.RequestToServer;
import com.ank.codeviewer.sender.enums.TypeHTTPRequest;

public class RequestStructureBuilder {
    public RequestStructure build(RequestToServer requestToServer){
        RequestStructure result = null;

        switch (requestToServer) {
            case GET_TEST_CONNECT -> {
                result = new RequestStructure(TypeHTTPRequest.GET);
                result.setPath("test");
                result.addHeader("Accept", "application/json");
            }
            case GET_ALL_LANGCODE -> {
                result = new RequestStructure(TypeHTTPRequest.GET);
                result.setPath("lang_code");
                result.addHeader("Accept", "application/json");
            }
            case GET_USER_ID -> {
                result = new RequestStructure(TypeHTTPRequest.GET);
                result.setPath("user/{id}");
                result.addHeader("Accept", "application/json");
            }
            case CREATE_POST -> {
                result = new RequestStructure(TypeHTTPRequest.POST);
                result.setPath("post");
                result.addHeader("Content-Type", "application/json");
            }
            case UPDATE_POST -> {
                result = new RequestStructure(TypeHTTPRequest.PATCH);
                result.setPath("post/{id}");
                result.addHeader("Content-Type", "application/json");
            }
            case FIND_POST -> {
                result = new RequestStructure(TypeHTTPRequest.GET);
                result.setPath("post/{id}");
                result.addHeader("Accept", "application/json");
            }
            case GET_PAGE_POSTS -> {
                result = new RequestStructure(TypeHTTPRequest.GET);
                result.setPath("post");
                result.addHeader("Accept", "application/json");
            }
            case CREATE_USER -> {
                result = new RequestStructure(TypeHTTPRequest.POST);
                result.setPath("user");
                result.addHeader("Content-Type", "application/json");
            }
            case AUTHENTICATION -> {
                result = new RequestStructure(TypeHTTPRequest.GET);
                result.setPath("user/au/{login}");
                result.addHeader("Content-Type", "application/json");
            }
            case FIND_GRADE -> {
                result = new RequestStructure(TypeHTTPRequest.GET);
                result.setPath("grade-post/user/{userId}/post/{postId}");
                result.addHeader("Accept", "application/json");
            }
            case CREATE_GRADE_POST -> {
                result = new RequestStructure(TypeHTTPRequest.POST);
                result.setPath("grade-post");
                result.addHeader("Content-Type", "application/json");
            }
            case CREATE_COMMENT_POST -> {
                result = new RequestStructure(TypeHTTPRequest.POST);
                result.setPath("comment");
                result.addHeader("Content-Type", "application/json");
            }
            case GET_PAGE_COMMENTS_POST -> {
                result = new RequestStructure(TypeHTTPRequest.GET);
                result.setPath("comment/pages/{postId}");
                result.addHeader("Accept", "application/json");
            }
            case DELETE_POST -> {
                result = new RequestStructure(TypeHTTPRequest.DELETE);
                result.setPath("post/{postId}");
                result.addHeader("Accept", "application/json");
            }
            default -> throw new RuntimeException("Не найден обработчик построителя для: " + requestToServer);
        }

        return result;
    }
}
