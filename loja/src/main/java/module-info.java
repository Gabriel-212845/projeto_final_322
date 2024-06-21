module projeto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires javafx.graphics;
    requires javafx.base;

    opens projeto to javafx.fxml;
    opens class01 to javafx.fxml;

    exports projeto;
    exports class01;
}
