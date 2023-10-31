package com.ank.codeviewer.view;

import com.ank.codeviewer.App;
import com.ank.codeviewer.controller.BaseWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Создает окно и контроллер окна
 */
public class WindowControllerBuilder {
    private String title;
    private Image icon;
    private  String uri;
    private Window owner;
    private boolean resizable;

    public WindowControllerBuilder(String title, Image icon) {
        this.title = title;
        this.icon = icon;
    }

    public WindowControllerBuilder start(String uri){
        resizable = true;
        this.owner = null;
        this.uri = uri;
        return this;
    }
    public WindowControllerBuilder owner(Window owner){
        this.owner = owner;

        return this;
    }
    public WindowControllerBuilder resizable(boolean resizable){
        this.resizable = resizable;
        return this;
    }
    public BaseWindowController build(){
        BaseWindowController controller = createWindow(uri);

        if (owner != null) {
            controller.getStage().initModality(Modality.WINDOW_MODAL);
            controller.getStage().initOwner(owner);
        }

        return controller;
    }
    /**
     * Создание окна
     * @param uri - путь к ресурсу
     * @return - базовый контроллер
     */
    private BaseWindowController createWindow(String uri){
        FXMLLoader loader = new FXMLLoader(App.class.getResource(uri));

//        if (App.class.getResource(uri) == null){
//            loader = new FXMLLoader(UserPropertyViewFactory.class.getResource(uri));
//        }

        Stage stage = createStage(loader);

        BaseWindowController result = loader.getController();
        result.setStage(stage);
        result.setUri(uri);

        //Событие при создании окна
        result.onCreate();

        //Событие при открытии формы
        stage.setOnShowing(windowEvent -> {
            result.onShow();
        });
        //Событие при сокрытии формы
        stage.setOnHiding(windowEvent -> {
            result.onHide();
        });
        //Событие при закрытии формы
        stage.setOnCloseRequest(e->{
            result.onClose();
        });

        //Спрячем форму при нажатии ESCAPE
        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) stage.hide();
        });

        return result;
    }
    private Stage createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);

        if (icon != null)
            stage.getIcons().add(icon);

        stage.setTitle(title);

        stage.setResizable(resizable);

        return stage;
    }
}
