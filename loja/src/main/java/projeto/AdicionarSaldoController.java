package projeto;

import java.io.IOException;
import java.util.List;

import class01.Caixa;
import class01.Gerente;
import class01.Pessoas;
import class01.escrArquivo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdicionarSaldoController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static Caixa caixa;
    public static Pessoas cliente;
    public static List<Gerente> Salvar;
    @SuppressWarnings("exports")
    @FXML public Label saldo;
    @SuppressWarnings("exports")
    @FXML public Label nome;
    @SuppressWarnings("exports")
    @FXML public Label message;

    @SuppressWarnings("exports")
    @FXML public TextField saldoAdd;


    @FXML
    public void initialize() {
        nome.setText(cliente.getNome());
        saldo.setText(String.valueOf(cliente.getSaldo()));
        message.setVisible(false);

    }



    


    @FXML
    private void adicionarSaldo(ActionEvent event) throws Exception {
        if(!saldoAdd.getText().equals("")){
            double add = Double.parseDouble(saldoAdd.getText());
            if(add>0){
                message.setVisible(false);
                cliente.adicionarSaldo(add);
                saldo.setText(String.valueOf(cliente.getSaldo()));
                saldoAdd.clear();
                escrArquivo.salvar(Salvar);

                message.setText("Saldo Adicionado!");
                message.setVisible(true);
            }
            
            
        }
    }





    @FXML
    private void switchComprar(ActionEvent event) throws IOException {
        ComprarController.Salvar = Salvar;
        ComprarController.caixa = caixa;
        ComprarController.cliente = cliente;
        root = FXMLLoader.load(getClass().getResource("Comprar.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
