module projeto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    opens projeto to javafx.fxml;
    exports projeto;
}
