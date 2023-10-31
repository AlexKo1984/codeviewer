package com.ank.codeviewer.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpPathBuilderTest {

    @Test
    void build() {
        HttpPathBuilder builder = new HttpPathBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put("id", 123);
        params.put("msg", "qwe");

        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("id", 123);

        String result = builder.build("user/{id}", params, pathParams);

        assertEquals("user/123?msg=qwe&id=123", result);
    }

    @Test
    void buildPath() {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("id", 123);
        pathParams.put("test", "qwe");

        HttpPathBuilder builder = new HttpPathBuilder();
        builder.setPathTemplate ("user");
        builder.setPathParams (pathParams);

        String result = builder.buildPath();
        assertEquals("user", result);

        builder.setPathTemplate ("user/{id}");
        result = builder.buildPath();
        assertEquals("user/123", result);

        builder.setPathTemplate ("user/{id}/qwe/{test}");
        result = builder.buildPath();
        assertEquals("user/123/qwe/qwe", result);
    }

    @Test
    void buildParametrs() {
        HttpPathBuilder builder = new HttpPathBuilder();

        Map<String, Object> params = new HashMap<>();
        builder.setParams(params);
        params.put("id", 123);

        String result = builder.buildParametrs();
        assertEquals("?id=123", result);

        params.put("msg", "qwe");

        result = builder.buildParametrs();
        assertEquals("?msg=qwe&id=123", result);

        params.put("date", LocalDate.of(2023, 10, 5));
        result = builder.buildParametrs();
        assertEquals("?msg=qwe&date=2023-10-05&id=123", result);
    }
}