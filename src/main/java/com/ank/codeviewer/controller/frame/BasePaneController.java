package com.ank.codeviewer.controller.frame;

import javafx.scene.layout.Pane;

/**
 * Базовый класс контроллера для наследования другими контроллерами панелей
 */
public class BasePaneController {
    private Pane root;

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }
}
