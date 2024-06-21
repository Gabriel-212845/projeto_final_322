package projeto;

import java.io.IOException;
import java.util.List;

import class01.Caixa;
import class01.Gerente;
import class01.escrArquivo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CaixaController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static Caixa caixa;
    public static List<Gerente> Salvar;
    @SuppressWarnings("exports")
    @FXML public Label nome;
    @SuppressWarnings("exports")
    @FXML public Label salario;
    @SuppressWarnings("exports")
    @FXML public Label aumentoStatus;
    



    @FXML
    public void initialize() {
        nome.setText(caixa.getNome());
        salario.setText(String.valueOf(caixa.getSalario()));
        if(caixa.getPedidoDeAumento() == null){
            aumentoStatus.setVisible(false);
        } else{
            aumentoStatus.setText("Pedido de aumento em análise.");
        }
        
    }

    @FXML
    private void PedirAumento(ActionEvent event) throws Exception {
        if(caixa.getPedidoDeAumento() == null){
            caixa.pedirAumento();
            aumentoStatus.setText("Pedido de aumento em análise.");
            aumentoStatus.setVisible(true);
            escrArquivo.salvar(Salvar);
        }
    }







    @FXML
    private void switchToPedidosCompra(ActionEvent event) throws IOException {
        PedidosCompraController.Salvar = Salvar;
        PedidosCompraController.caixa = caixa;
        root = FXMLLoader.load(getClass().getResource("PedidosCompra.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToPedidosCaixa(ActionEvent event) throws IOException {
        PedidosCaixaController.Salvar = Salvar;
        PedidosCaixaController.caixa = caixa;
        root = FXMLLoader.load(getClass().getResource("PedidosCaixa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
