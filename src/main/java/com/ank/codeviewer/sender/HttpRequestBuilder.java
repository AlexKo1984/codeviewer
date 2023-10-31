package com.ank.codeviewer.sender;

import com.ank.codeviewer.model.Model;
import com.ank.codeviewer.model.SystemUser;
import com.ank.codeviewer.model.enums.TypeSystemUser;
import com.ank.codeviewer.sender.enums.TypeHTTPRequest;
import com.ank.codeviewer.util.HttpPathBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Base64;
import java.util.Map;

/**
 * Построитель запроса
 */
@Getter
@Setter
public class HttpRequestBuilder {
    private final ObjectMapper objectMapper;
    private final HttpPathBuilder httpPathBuilder;
    /**
     * Адрес сервера. Напр http://localhost:8080
     */
    private String serverAddress;
    private RequestStructure structure;

    public HttpRequestBuilder(String serverAddress, ObjectMapper objectMapper, HttpPathBuilder httpPathBuilder) {
        this.objectMapper = objectMapper;
        this.serverAddress = serverAddress;
        this.httpPathBuilder = httpPathBuilder;
    }

    private URI buildUri(){
        String path = httpPathBuilder.build(structure.getPath(), structure.getParams(), structure.getPathParams());

        String address = path.isEmpty() ? serverAddress : serverAddress + "/" + path;
        return URI.create(address);
    }

    private String getBodyJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(structure.getDtoToJson());
    }
    private void richedHeader(HttpRequest.Builder builder) {
        for (Map.Entry<String, String> header: structure.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }
    }
    public HttpRequest build(RequestStructure structur) throws JsonProcessingException {
        this.structure = structur;

        HttpRequest.BodyPublisher bodyPublisher = null;

        //Создаем объект, описывающий HTTP запрос
        HttpRequest.Builder builder = HttpRequest.newBuilder()//Получаем экземпляр билдера
                .uri(buildUri())//Указываем адрес ресурса
                .version(HttpClient.Version.HTTP_1_1);//Указываем версию протокола
        if (structure.isExistHeader()) {
            richedHeader(builder);
        }
        if (structure.isExistDto()) {
            String json = getBodyJson();
            bodyPublisher = HttpRequest.BodyPublishers.ofString(json);
        }
        if (structure.getHttpRequest() == TypeHTTPRequest.POST) {
            builder.POST(bodyPublisher);//Указываем HTTP метод запроса
        }
        if (structure.getHttpRequest() == TypeHTTPRequest.PATCH) {
            builder.method("PATCH", bodyPublisher);
        }
        if (structure.getHttpRequest() == TypeHTTPRequest.DELETE) {
            builder.DELETE();
        }

        SystemUser systemUser = Model.getInstance().getSystemUser();
        if (systemUser.getTypeUser() != TypeSystemUser.UNREGISTERED_USER)
            builder.header("Authorization", getBasicAuthenticationHeader(systemUser.getLogin(), systemUser.getPassword()));

        return builder.build();
    }
    private String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
