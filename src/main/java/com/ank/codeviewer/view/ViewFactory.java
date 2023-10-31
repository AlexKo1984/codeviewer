package com.ank.codeviewer.view;

import com.ank.codeviewer.controller.*;
import com.ank.codeviewer.controller.frame.PageNavigatorController;
import javafx.stage.Window;

import java.io.IOException;

public class ViewFactory {
    private String title;
    private final WindowControllerBuilder windowControllerBuilder;
    private final PaneControllerBuilder paneControllerBuilder;

    public ViewFactory(String title, WindowControllerBuilder windowControllerBuilder, PaneControllerBuilder paneControllerBuilder) {
        this.windowControllerBuilder = windowControllerBuilder;
        this.paneControllerBuilder = paneControllerBuilder;
        this.title = title;
    }

    public PaneControllerBuilder getPaneControllerBuilder() {
        return paneControllerBuilder;
    }

    public PostsWindowController getMainWindowController() {
//        return (HelloController)windowControllerBuilder.start("hello-view.fxml")
//                .build();
        PostsWindowController controller = (PostsWindowController)windowControllerBuilder
                .start("/FXML/PostsWindow.fxml")
                .build();
        //controller.getHboxPageNavigator();

        try {
            PageNavigatorController pageNavigatorController = (PageNavigatorController) getPaneControllerBuilder()
                    .start(controller.getHboxPageNavigator(), "/FXML/frame/PageNavigator.fxml")
                    .build();
            controller.setPageNavigatorController(pageNavigatorController);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return controller;
    }

    public SystemUserPropertyWindowController getSystemUserPropertyWindowController(Window owner) {
        return (SystemUserPropertyWindowController)windowControllerBuilder
                .start("/FXML/SystemUserPropertyWindow.fxml")
                .owner(owner)
                .build();
    }
    public AuthenticationSystemUserWindowController getAuthenticationSystemUserWindowController(Window owner) {
        return (AuthenticationSystemUserWindowController)windowControllerBuilder
                .start("/FXML/AuthenticationSystemUserWindow.fxml")
                .owner(owner)
                .resizable(false)
                .build();
    }
    public ViewPostWindowController getPostWindowController(Window owner) {
        ViewPostWindowController controller = (ViewPostWindowController) windowControllerBuilder
                .start("/FXML/ViewPostWindow.fxml")
                .owner(owner)
                .build();

        try {
            PageNavigatorController pageNavigatorController = (PageNavigatorController) getPaneControllerBuilder()
                    .start(controller.getHboxPageNavigator(), "/FXML/frame/PageNavigator.fxml")
                    .build();
            controller.setPageNavigatorController(pageNavigatorController);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return controller;
    }

    /**
     * Форма редактирования
     */
    public EditPostWindowController getEditPostWindowController(Window owner) {
        return (EditPostWindowController) windowControllerBuilder
                .start("/FXML/EditPostWindow.fxml")
                .owner(owner)
                .build();
    }
    /**
     * Форма комментария поста
     */
    public AddCommentPostWindowController getAddCommentPostWindowController(Window owner) {
        return (AddCommentPostWindowController) windowControllerBuilder
                .start("/FXML/AddCommentPost.fxml")
                .owner(owner)
                .build();
    }
}
