package projeto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import class01.Caixa;
import class01.Celular;
import class01.Gerente;
import class01.Pessoas;
import class01.Produtos;
import class01.Tablet;
import class01.Tv;
import class01.escrArquivo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PedidosGerenteController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static Gerente gerente;
    public static List<Gerente> Salvar;

    @SuppressWarnings({ "exports", "rawtypes" })
    @FXML public ChoiceBox tipoChoiseBox;
    @SuppressWarnings({ "exports", "rawtypes" })
    @FXML public ChoiceBox caixasChoiseBox;
    @SuppressWarnings({ "exports", "rawtypes" })
    @FXML public ChoiceBox pedidoChoiseBox;

    @SuppressWarnings("exports")
    @FXML public Label espc1;
    @SuppressWarnings("exports")
    @FXML public Label espc2;
    @SuppressWarnings("exports")
    @FXML public Label espc3;
    @SuppressWarnings("exports")
    @FXML public Label espc4;
    @SuppressWarnings("exports")
    @FXML public Label espc5;
    @SuppressWarnings("exports")
    @FXML public Label espc6;

    @SuppressWarnings("exports")
    @FXML public TextField nome;
    @SuppressWarnings("exports")
    @FXML public TextField marca;
    @SuppressWarnings("exports")
    @FXML public TextField fornecedor;
    @SuppressWarnings("exports")
    @FXML public TextField precoDeCusto;
    @SuppressWarnings("exports")
    @FXML public TextField precoDeVenda;
    @SuppressWarnings("exports")
    @FXML public TextField path;
    @SuppressWarnings("exports")
    @FXML public TextField esp1;
    @SuppressWarnings("exports")
    @FXML public TextField esp2;
    @SuppressWarnings("exports")
    @FXML public TextField esp3;
    @SuppressWarnings("exports")
    @FXML public TextField esp4;
    @SuppressWarnings("exports")
    @FXML public TextField esp5;
    @SuppressWarnings("exports")
    @FXML public TextField esp6;
    @SuppressWarnings("exports")
    @FXML public TextField quantidade;

    public List<Produtos> listaDeProdutos = new ArrayList<>();
    //@SuppressWarnings("exports")
    public List<Integer> listaDeQuantidade = new ArrayList<>();

    //@SuppressWarnings("exports")
    public ObservableList<String> listaTipoProdutos = FXCollections.observableArrayList("Tv", "Tablet", "Celular");
    public ObservableList<Pessoas> listaCaixasContrados = FXCollections.observableArrayList(gerente.getCaixasContratados());
    public ObservableList<Produtos> listaPedidoProdutos = FXCollections.observableArrayList(listaDeProdutos);


    @SuppressWarnings("unchecked")
    @FXML
    private void initialize(){
        caixasChoiseBox.setItems(listaCaixasContrados);
        clearAll();
    }

    @FXML
    private void atualizarTipoBox(){
        try {
            if(tipoChoiseBox.getValue().equals("Tv")){
                espc1.setText("Tamanho Da Tela (pol): ");
                espc2.setText("Resolução da Tela: ");
                espc3.setText("Tipo de Tela: ");
                espc4.setText("É smart (Sim/Não): ");
                espc5.setText("Acompanha suporte (Sim/Não): ");
                espc6.setText("Consumo de Energia (kWh): ");
            } else if(tipoChoiseBox.getValue().equals("Tablet")){
                espc1.setText("Tamanho Da Tela (pol): ");
                espc2.setText("Armazenamento (GB): ");
                espc3.setText("Resolução da Câmera (MP): ");
                espc4.setText("Capacidade da Bateria (mAh) : ");
                espc5.setText("Acompanha Caneta (Sim/Não): ");
                espc6.setText("Acompanha teclado (Sim/Não): ");
            } else if(tipoChoiseBox.getValue().equals("Celular")){
                espc1.setText("Tamanho Da Tela (pol): ");
                espc2.setText("Quantidade de Chips Suportados: ");
                espc3.setText("Armazenamento (GB): ");
                espc4.setText("Resolução da Câmera (MP): ");
                espc5.setText("Capacidade da Bateria (mAh) : ");
                espc6.setText("Quais as Resistências do Aparelho: ");
            } else{
                espc1.setText("Selecione o tipo de produto");
                espc2.setText("Selecione o tipo de produto");
                espc3.setText("Selecione o tipo de produto");
                espc4.setText("Selecione o tipo de produto");
                espc5.setText("Selecione o tipo de produto");
                espc6.setText("Selecione o tipo de produto");
            }
        } catch (Exception e) {}
            
    }


    



    @SuppressWarnings("unchecked")
    @FXML
    private void retirarDoPedido(ActionEvent event) throws IOException {
        if(pedidoChoiseBox.getValue() != null){
            int index = listaDeProdutos.indexOf((Produtos) pedidoChoiseBox.getValue());
            listaDeProdutos.remove((Produtos) pedidoChoiseBox.getValue());
            listaDeQuantidade.remove(index);
            listaPedidoProdutos = FXCollections.observableArrayList(listaDeProdutos);
            pedidoChoiseBox.setItems(listaPedidoProdutos);

        }
    }

    @SuppressWarnings("unchecked")
    @FXML
    private void fazerPedido(ActionEvent event) throws Exception {
        if(caixasChoiseBox.getValue() != null && !listaDeProdutos.isEmpty() && !listaDeQuantidade.isEmpty()){
            int aux = -1;
            Caixa caixa = (Caixa) caixasChoiseBox.getValue();
            for(int i=0; i < gerente.getCaixasContratados().size(); i++){
                if(gerente.getCaixasContratados().get(i).getId().equals(caixa.getId())){
                    aux = i;
                    break;
                }
            }
            if(aux>=0){
                gerente.solicitarCompra(Produtos.DeepCopyList(listaDeProdutos), deepCopyInteger(listaDeQuantidade),
                                        gerente.getCaixasContratados().get(aux));

                listaCaixasContrados = FXCollections.observableArrayList(gerente.getCaixasContratados());
                caixasChoiseBox.setItems(listaCaixasContrados);
                listaDeProdutos.clear();
                listaDeQuantidade.clear();
                listaPedidoProdutos = FXCollections.observableArrayList(listaDeProdutos);
                pedidoChoiseBox.setItems(listaPedidoProdutos);

                caixasChoiseBox.setItems(listaCaixasContrados);
                clearAll();
                //System.out.println(gerente.getCaixasContratados().get(aux).ListarPedidosDeEstoque());
            }
            escrArquivo.salvar(Salvar);

        }
    }

    @SuppressWarnings("unchecked")
    @FXML
    private void adicionarAoPedido(ActionEvent event) throws IOException {
        if(!nome.getText().equals("") && !marca.getText().equals("") && !fornecedor.getText().equals("")
           && !precoDeCusto.getText().equals("") && !precoDeVenda.getText().equals("")
           && !path.getText().equals("") && !esp1.getText().equals("")&& !esp2.getText().equals("")
           && !esp3.getText().equals("") && !esp4.getText().equals("") && !esp5.getText().equals("")
           && !esp6.getText().equals("") && !quantidade.getText().equals("")){

            String fullPath = "projeto_final_322\\loja\\src\\main\\java\\class01\\Imagens\\";

            try {
                
                if(tipoChoiseBox.getValue().equals("Tv")){
                    int quant = Integer.valueOf(quantidade.getText());
                    Tv tv = new Tv(Double.parseDouble(precoDeCusto.getText()), Double.parseDouble(precoDeVenda.getText()),
                                   nome.getText(), marca.getText(), fornecedor.getText(), false, fullPath + path.getText(),
                                   Double.parseDouble(esp1.getText()), esp2.getText(), esp3.getText(), StrToBoo(esp4.getText()),
                                   StrToBoo(esp5.getText()), Double.parseDouble(esp6.getText()));
                    listaDeProdutos.add(tv);
                    listaDeQuantidade.add(quant);
                    listaPedidoProdutos = FXCollections.observableArrayList(listaDeProdutos);
                    pedidoChoiseBox.setItems(listaPedidoProdutos);
                    clearAll();

                } else if(tipoChoiseBox.getValue().equals("Tablet")){
                    int quant = Integer.valueOf(quantidade.getText());
                    int armazenamento = Integer.valueOf(esp2.getText());
                    int resolucaoCamera = Integer.valueOf(esp3.getText());
                    int capacidadeBateria = Integer.valueOf(esp4.getText());

                    Tablet tablet = new Tablet(Double.parseDouble(precoDeCusto.getText()), Double.parseDouble(precoDeVenda.getText()),
                                    nome.getText(), marca.getText(), fornecedor.getText(), false, fullPath + path.getText(),
                                    Double.parseDouble(esp1.getText()), armazenamento, resolucaoCamera, capacidadeBateria,
                                    StrToBoo(esp5.getText()), StrToBoo(esp6.getText()));
                    listaDeProdutos.add(tablet);
                    listaDeQuantidade.add(quant);
                    listaPedidoProdutos = FXCollections.observableArrayList(listaDeProdutos);
                    pedidoChoiseBox.setItems(listaPedidoProdutos);
                    clearAll();

                } else if(tipoChoiseBox.getValue().equals("Celular")){
                    int quant = Integer.valueOf(quantidade.getText());
                    int qtdChips = Integer.valueOf(esp2.getText());
                    int armazenamento = Integer.valueOf(esp3.getText());
                    int resolucaoCamera = Integer.valueOf(esp4.getText());
                    int capacidadeBateria = Integer.valueOf(esp5.getText());

                   Celular celular = new Celular(Double.parseDouble(precoDeCusto.getText()), Double.parseDouble(precoDeVenda.getText()),
                    nome.getText(), marca.getText(), fornecedor.getText(), false, fullPath + path.getText(),
                    Double.parseDouble(esp1.getText()), qtdChips, armazenamento, resolucaoCamera, capacidadeBateria, esp6.getText());
                    listaDeProdutos.add(celular);
                    listaDeQuantidade.add(quant);
                    listaPedidoProdutos = FXCollections.observableArrayList(listaDeProdutos);
                    pedidoChoiseBox.setItems(listaPedidoProdutos);
                    clearAll();

                }


            } catch (Exception e) {}




        }
    }

    private boolean StrToBoo(String str){
        if(str.equals("sim") || str.equals("Sim") || str.equals("SIM") || str.equals("true")
        || str.equals("True")|| str.equals("TRUE")) return true;
        else if(str.equals("não") || str.equals("Não") || str.equals("NÂO")
           || str.equals("nao") || str.equals("Nao") || str.equals("NAO")
           || str.equals("false") || str.equals("False") || str.equals("FALSE")) return false;
        else throw new Error("Não é boolean");
    }

    @SuppressWarnings("unchecked")
    private void clearAll(){
        nome.clear();
        marca.clear();
        fornecedor.clear();
        precoDeCusto.clear();
        precoDeVenda.clear();
        path.clear();
        esp1.clear();
        esp2.clear();
        esp3.clear();
        esp4.clear();
        esp5.clear();
        esp6.clear();
        quantidade.clear();
        listaTipoProdutos = FXCollections.observableArrayList("Tv", "Tablet", "Celular");
        tipoChoiseBox.setItems(listaTipoProdutos);

    }

    @SuppressWarnings("deprecation")
    public List<Integer> deepCopyInteger(List<Integer> listInteger){
        List<Integer> copy = new ArrayList<>();
        for(int i = 0; i<listInteger.size(); i++){
            copy.add(new Integer(listInteger.get(i)));
        }
        return copy;
    }



    @FXML
    private void switchToGerenciamento(ActionEvent event) throws IOException {
        GerenciamentoController.Salvar = Salvar;
        GerenciamentoController.gerente = gerente;
        root = FXMLLoader.load(getClass().getResource("Gerenciamento.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
