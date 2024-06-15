package projeto;

import java.io.IOException;

import class01.Caixa;
import class01.Gerente;
import class01.Pessoas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CaixasContratadosController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @SuppressWarnings("exports")
    public static Gerente gerente;
    @SuppressWarnings({ "exports", "rawtypes" })
    @FXML public ChoiceBox caixasChoiseBox;
    @SuppressWarnings("exports")
    @FXML public TextField nome;
    @SuppressWarnings("exports")
    @FXML public TextField id;
    @SuppressWarnings("exports")
    @FXML public TextField salario;


    @SuppressWarnings("exports")
    public ObservableList<Pessoas> listaCaixasContrados = FXCollections.observableArrayList(gerente.getCaixasContratados());
    



    @SuppressWarnings("unchecked")
    @FXML
    public void initialize() {
        caixasChoiseBox.setItems(listaCaixasContrados);
    }

    @SuppressWarnings("unchecked")
    @FXML
    private void demitirFuncionário(ActionEvent event) throws IOException {
        if(caixasChoiseBox.getValue() != null){
            gerente.demitirCaixa((Caixa) caixasChoiseBox.getValue());
            listaCaixasContrados = FXCollections.observableArrayList(gerente.getCaixasContratados());
            caixasChoiseBox.setItems(listaCaixasContrados);

        }
    }

    @SuppressWarnings("unchecked")
    @FXML
    private void contratarFuncionário(ActionEvent event) throws IOException {
        if(!nome.getText().equals("") && !id.getText().equals("") && !salario.getText().equals("")){
            try{
                double sal = Double.parseDouble(salario.getText());
                gerente.contratarCaixa(new Caixa(nome.getText(), id.getText(), 0, sal));

                listaCaixasContrados = FXCollections.observableArrayList(gerente.getCaixasContratados());
                caixasChoiseBox.setItems(listaCaixasContrados);

                nome.clear();
                id.clear();
                salario.clear();

            }catch(NumberFormatException e){}
        }
    }

    @FXML
    private void switchToGerenciamento(ActionEvent event) throws IOException {
        GerenciamentoController.gerente = gerente;
        root = FXMLLoader.load(getClass().getResource("Gerenciamento.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
