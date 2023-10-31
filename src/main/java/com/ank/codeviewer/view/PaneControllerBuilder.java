package com.ank.codeviewer.view;

import com.ank.codeviewer.App;
import com.ank.codeviewer.controller.frame.BasePaneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Создает панель и контроллер панели
 */
public class PaneControllerBuilder {
    private Pane root;
    private String uri;

    public PaneControllerBuilder start(Pane root, String uri){
        this.root = root;
        this.uri = uri;

        return this;
    }
    public BasePaneController build() throws IOException {
        BasePaneController result = loadPane();

        return result;
    }
    private BasePaneController loadPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(uri));
        Pane pane = loader.load();//Панель, которую вставляем

        root.getChildren().add(pane);

        if (root.getClass().getName().equals(AnchorPane.class.getName())){
            setAnchorNode((AnchorPane) pane,1,1,1,1);
        }

        BasePaneController result = loader.getController();
        result.setRoot(pane);

        return result;
    }
    private void setAnchorNode(Node node, double top, double left, double right, double bottom) {
        if (-1 < top) {
            AnchorPane.setTopAnchor(node, top);
        }
        if (-1 < left) {
            AnchorPane.setLeftAnchor(node, left);
        }
        if (-1 < right) {
            AnchorPane.setRightAnchor(node, right);
        }
        if (-1 < bottom) {
            AnchorPane.setBottomAnchor(node, bottom);
        }
    }
}
