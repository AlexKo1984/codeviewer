package com.ank.codeviewer;

import com.ank.codeviewer.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Model.getInstance().getClientPostService().showPosts();
    }

    public static void main(String[] args) {
        launch();
    }
}