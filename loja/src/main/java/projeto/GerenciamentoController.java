package projeto;

import java.io.IOException;

import class01.Gerente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GerenciamentoController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @SuppressWarnings("exports")
    public static Gerente gerente;
    @SuppressWarnings("exports")
    @FXML public Label cofre;
    @SuppressWarnings("exports")
    @FXML public Label nome;
    



    @FXML
    public void initialize() {
        nome.setText(gerente.getNome());
        cofre.setText(String.valueOf(Gerente.getCofre()));
    }

    @FXML
    private void switchToCaixasContratados(ActionEvent event) throws IOException {
        CaixasContratadosController.gerente = gerente;
        root = FXMLLoader.load(getClass().getResource("CaixasContratados.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToPedidosGerente(ActionEvent event) throws IOException {
        PedidosGerenteController.gerente = gerente;
        root = FXMLLoader.load(getClass().getResource("PedidosGerente.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
