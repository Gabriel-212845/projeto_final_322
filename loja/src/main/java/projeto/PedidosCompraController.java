package projeto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import class01.Caixa;
import class01.Compra;
import class01.Gerente;
import class01.escrArquivo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PedidosCompraController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static Caixa caixa;
    public static List<Gerente> Salvar;
    @SuppressWarnings("exports")
    @FXML public Label message;

    @FXML private TableView<Compra> tableView;

    private ObservableList<Compra> compras = FXCollections.observableArrayList();

    
    @FXML private TableColumn<Compra, LocalDate> dataColumn;
    @FXML private TableColumn<Compra, Double> precoColumn;
    
    @FXML
    public void initialize() {
        message.setVisible(false);

        for(int i = 0; i < caixa.getClientes().size(); i++){
            for(int j=0; j<caixa.getClientes().get(i).getComprasAguardandoPostagem().size(); j++){
                compras.add(caixa.getClientes().get(i).getComprasAguardandoPostagem().get(j));
            }
        }


        dataColumn.setCellValueFactory(new PropertyValueFactory<Compra, LocalDate>("dataDoPagamento"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<Compra, Double>("valorPago"));

        //load dummy data
        tableView.setItems(compras);

        tableView.setEditable(false);




    }
    

    @FXML
    private void DespacharTodosOsPedidos(ActionEvent event) throws Exception {
        if(compras.size() == 0){
            message.setText("Não há compras aguardando postagem!");
            message.setVisible(true);
        } else{
            caixa.enviarPedidos();
            escrArquivo.salvar(Salvar);

            compras.clear();
            for(int i = 0; i < caixa.getClientes().size(); i++){
                for(int j=0; j<caixa.getClientes().get(i).getComprasAguardandoPostagem().size(); j++){
                    compras.add(caixa.getClientes().get(i).getComprasAguardandoPostagem().get(j));
                }
            }
            tableView.setItems(compras);

            message.setText("Efetuado com êxito!");
            message.setVisible(true);


        }
    }







    @FXML
    private void switchToCaixa(ActionEvent event) throws IOException {
        CaixaController.Salvar = Salvar;
        CaixaController.caixa = caixa;
        root = FXMLLoader.load(getClass().getResource("Caixa.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
