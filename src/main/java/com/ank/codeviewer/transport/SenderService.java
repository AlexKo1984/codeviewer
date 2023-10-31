package com.ank.codeviewer.transport;

import com.ank.codeviewer.model.ErrorResponse;
import com.ank.codeviewer.sender.HttpRequestBuilder;
import com.ank.codeviewer.sender.RequestStructure;
import com.ank.codeviewer.sender.ResponseStructure;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервис отправки запросов
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SenderService {
    private final HttpRequestBuilder httpRequestBuilder;
    private final ObjectMapper objectMapper;

    public <T> ResponseStructure<T> send(RequestStructure structure, Class<T> dtoClass){
        HttpResponse<String> response = null;

        try {
            HttpRequest httpRequest = httpRequestBuilder.build(structure);
            response = sendToServer(httpRequest);
            logRequest(httpRequest, response);

            ResponseStructure<T> responseStructure = null;

            try {
                responseStructure = mapToResponseStructure(response, dtoClass);
            } catch (JsonProcessingException e) {
//Если приходит не тот формат, который ожидали
                responseStructure = mapToResponseStructure(response);
            }

            logRequest(httpRequest, response);

            return responseStructure;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void logRequest(HttpRequest httpRequest, HttpResponse<String> response) {
        System.out.println("*********************************************************");
        System.out.println(httpRequest.uri());
        System.out.println("<<<------------------------------------------------------");
        System.out.println(response.body());
    }

    public HttpResponse<String> sendToServer(HttpRequest request) throws IOException, InterruptedException {
        //HTTP-клиент с настройками по умолчанию
        HttpClient client = HttpClient.newHttpClient();

        //Получаем стандартный обработчик тела запроса с конвертацией содержимого в строку
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

        //Отправляем запрос и получаем ответ от сервера
        HttpResponse<String> response = client.send(request, handler);

        return response;
    }
    private <T> ResponseStructure<T> mapToResponseStructure(HttpResponse<String> response, Class<T> dtoClass) throws JsonProcessingException {
        ResponseStructure<T> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(response.statusCode());

        responseStructure.setOk(response.statusCode() == 200);
        if (responseStructure.isOk()){
            //Если возвращается void
            if (dtoClass != Object.class) {
                T o = objectMapper.readValue(response.body(), dtoClass);
                responseStructure.setDtoObject(o);
            }
        }else {
            ErrorResponse errorResponse = objectMapper.readValue(response.body(), ErrorResponse.class);
            responseStructure.setErrorMessage(errorResponse.getError());
        }

        return responseStructure;
    }

    /**
     * Если приходит не тот формат, который ожидали
     */
    private <T> ResponseStructure<T> mapToResponseStructure(HttpResponse<String> response) throws JsonProcessingException {
        ResponseStructure<T> responseStructure = new ResponseStructure<>();

        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
        Map<String, String> map = objectMapper.readValue(response.body(), typeRef);

        if (map.containsKey("status")){
            responseStructure.setStatusCode(Integer.parseInt(map.get("status")));
        }
        if (map.containsKey("error")){
            responseStructure.setErrorMessage(map.get("error"));
        }

        return responseStructure;
    }
}
