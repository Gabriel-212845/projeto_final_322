package projeto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import class01.Caixa;
import class01.Gerente;
import class01.PedidoDeEstoque;
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

public class PedidosCaixaController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static Caixa caixa;
    public static List<Gerente> Salvar;
    @SuppressWarnings("exports")
    @FXML public Label message;

    @FXML private TableView<PedidoDeEstoque> tableView;

    private ObservableList<PedidoDeEstoque> pedidos = FXCollections.observableArrayList();

    
    @FXML private TableColumn<PedidoDeEstoque, LocalDate> dataColumn;
    @FXML private TableColumn<PedidoDeEstoque, Double> precoColumn;
    
    @FXML
    public void initialize() {
        message.setVisible(false);

        for(int i = 0; i < caixa.getPedidosDeEstoque().size(); i++){
            pedidos.add(caixa.getPedidosDeEstoque().get(i));
        }


        dataColumn.setCellValueFactory(new PropertyValueFactory<PedidoDeEstoque, LocalDate>("data"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<PedidoDeEstoque, Double>("valorTotal"));

        //load dummy data
        tableView.setItems(pedidos);

        tableView.setEditable(false);




    }
    

    @FXML
    private void FazerTodosOsPedidos(ActionEvent event) throws Exception {
        if(pedidos.size() == 0){
            message.setText("Não há pedidos a fazer!");
            message.setVisible(true);
        } else{
            caixa.reporEstoque();
            escrArquivo.salvar(Salvar);

            pedidos.clear();
            for(int i = 0; i < caixa.getPedidosDeEstoque().size(); i++){
                pedidos.add(caixa.getPedidosDeEstoque().get(i));
            }
            tableView.setItems(pedidos);

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
