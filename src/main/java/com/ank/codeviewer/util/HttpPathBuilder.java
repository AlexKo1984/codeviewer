package com.ank.codeviewer.util;

import lombok.Setter;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Собирает путь http вида user/1?msg=qwe&size=1
 */
@Setter
public class HttpPathBuilder {
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;//YYY-MM-DD
    private String pathTemplate;//Шаблон пути вида user/{id}
    private Map<String, Object> params;//Для сборки строки ?msg=qwe&size=1
    private Map<String, Object> pathParams;//Для сборки строки user/1 из user/{id}

    public String build(String pathTemplate, Map<String, Object> params, Map<String, Object> pathParams) {
        this.pathTemplate = pathTemplate;
        this.params = params;
        this.pathParams = pathParams;

        return buildPath() + buildParametrs();
    }

    public String buildParametrs() {
        if (params.size() == 0) return "";

        List<String> strings = new ArrayList<>(params.size());

        for (Map.Entry<String, Object> param: params.entrySet()){
            String val = param.getKey() + "=" + valueToString(param.getValue());
            strings.add(val);
        }

        return "?" + String.join("&", strings);
    }
    public String buildPath() {
        if (pathParams.size() == 0) return pathTemplate;

        String[] strings = pathTemplate.split("/");
        List<String> newStrings = new ArrayList<>();

        for (String str: strings){
            boolean isParamName = str.startsWith("{");

            if (! isParamName){
                newStrings.add(str);
                continue;
            };

            String key = str.substring(1, str.length() - 1);//Уберем скобки

            if (pathParams.containsKey(key)){
                newStrings.add(
                        valueToString(pathParams.get(key))
                );
            }
        }

        return String.join("/", newStrings);
    }

    private String valueToString(Object value) {
        if (value instanceof String){
            return String.valueOf(value);
        } else if (value instanceof LocalDate) {
            return ((LocalDate)value).format(formatter);
        } else if (value instanceof Integer) {
            return String.valueOf(value);
        } else if (value instanceof List<?>) {
            List<String> list = new ArrayList<>(((List<?>)value).size());

            for (Object obj: (List<?>)value) {
                list.add(valueToString(obj));
            }

            return String.join(",", list);
        }else {
            throw new RuntimeException("Не определен тип значения фильтра");
        }
    }
}
