module projeto {
    requires javafx.controls;
    requires javafx.fxml;

    opens projeto to javafx.fxml;
    exports projeto;
}
