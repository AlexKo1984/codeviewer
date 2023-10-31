module com.ank.codeviewer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires lombok;
    requires modelmapper;
    requires java.desktop;

    opens com.ank.codeviewer to javafx.fxml;
    opens com.ank.codeviewer.controller to javafx.fxml;
    opens com.ank.codeviewer.controller.frame to javafx.fxml;
    opens com.ank.codeviewer.model.post to javafx.fxml;
    exports com.ank.codeviewer;
    exports com.ank.codeviewer.dto;
    exports com.ank.codeviewer.sender;
    exports com.ank.codeviewer.model.post;
    opens com.ank.codeviewer.sender to javafx.fxml;
    exports com.ank.codeviewer.sender.enums;
    exports com.ank.codeviewer.model;
    opens com.ank.codeviewer.sender.enums to javafx.fxml;
    exports com.ank.codeviewer.dto.post;
    exports com.ank.codeviewer.dto.langcode;
}