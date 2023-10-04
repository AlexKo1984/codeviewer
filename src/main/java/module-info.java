module com.ank.codeviewer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ank.codeviewer to javafx.fxml;
    exports com.ank.codeviewer;
}