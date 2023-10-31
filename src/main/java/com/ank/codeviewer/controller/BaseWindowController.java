package com.ank.codeviewer.controller;

import javafx.stage.Stage;

/**
 * Базовый класс контроллера для наследования другими контроллерами окон
 */
public class BaseWindowController {
    /**
     * Основной стейдж окна
     */
    private Stage stage;
    /**
     * Имя ресурса. Полезно в отладке
     */
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Получить стейдж
     * @return - стейдж
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Установить стейдж
     * @param stage - стейдж
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Показать окно
     */
    public void show(){
        stage.show();
    }
    public void showAndWait(){
        stage.showAndWait();
    }
    /**
     * Стрыть окно
     */
    public void hide(){
        stage.hide();
    }

    /**
     * Закрыть окно
     */
    public void close(){
        stage.close();
    }

    /**
     * Событие при создании окна
     */
    public void onCreate(){

    }
    /**
     * Событие при открытии окна
     */
    public void onShow(){

    }
    /**
     * Событие при сткрытии окна
     */
    public void onHide(){

    }
    /**
     * Событие при закрытии окна
     */
    public void onClose(){

    }
    public boolean isShowing(){
        return stage.isShowing();
    }
    /**
     * Для возможности потомкам добавлять контроллеры
     * @param object
     */
    public void addController(Object object){

    }
}
