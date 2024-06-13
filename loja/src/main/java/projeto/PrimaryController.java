package projeto;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException {
        //App.setRoot("secondary");

        root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
