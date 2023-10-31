package com.ank.codeviewer.sender;

import com.ank.codeviewer.sender.enums.TypeHTTPRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Описание структуры HTTP запроса
 */
public class RequestStructure {
    private final TypeHTTPRequest httpRequest;
    /**
     * Путь в Uri. Напр /user
     */
    private String path = "";//Шаблон пути вида user/{id}
    private final Map<String, Object> params;//Для сборки строки ?msg=qwe&size=1
    private final Map<String, Object> pathParams;//Для сборки строки user/1 из user/{id}
    private final Map<String, String> headers;
    private Object dtoToJson;

    public RequestStructure(TypeHTTPRequest httpRequest) {
        this.httpRequest = httpRequest;
        headers = new HashMap<>();
        params = new HashMap<>();
        pathParams = new HashMap<>();
    }

    public void addHeader(String key, String value){
        headers.put(key, value);
    }
    public void addParam(String key, Object value){
        params.put(key, value);
    }
    public void addPathParam(String key, String value){
        pathParams.put(key, value);
    }

    public Object getDtoToJson() {
        return dtoToJson;
    }

    public void setDtoToJson(Object dtoToJson) {
        this.dtoToJson = dtoToJson;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isExistHeader() {
        return !headers.isEmpty();
    }

    public boolean isExistDto() {
        return dtoToJson != null;
    }

    public TypeHTTPRequest getHttpRequest() {
        return httpRequest;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public Map<String, Object> getPathParams() {
        return pathParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}

