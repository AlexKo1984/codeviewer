package com.ank.codeviewer.view;

public class BaseViewFactory {
    private final String title;
    private final WindowControllerBuilder windowControllerBuilder;
    private PaneControllerBuilder paneControllerBuilder;

    public BaseViewFactory(String title, WindowControllerBuilder windowControllerBuilder, PaneControllerBuilder paneControllerBuilder) {
        this.title = title;
        this.windowControllerBuilder = windowControllerBuilder;
        this.paneControllerBuilder = paneControllerBuilder;
    }

    public String getTitle() {
        return title;
    }

    public WindowControllerBuilder getWindowControllerBuilder() {
        return windowControllerBuilder;
    }

    public PaneControllerBuilder getPaneControllerBuilder() {
        return paneControllerBuilder;
    }

    public void setPaneControllerBuilder(PaneControllerBuilder paneControllerBuilder) {
        this.paneControllerBuilder = paneControllerBuilder;
    }
}
