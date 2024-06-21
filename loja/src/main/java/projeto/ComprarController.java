package projeto;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import class01.Caixa;
import class01.Celular;
import class01.Compra;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ComprarController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List<Produtos> produtosCopy = new ArrayList();
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List<Integer> quantidadeCopy = new ArrayList();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List<String> stringCompra = new ArrayList();

    public static Caixa caixa;
    public static Pessoas cliente;
    public static List<Gerente> Salvar;
    @SuppressWarnings("exports")
    @FXML public Label saldo;
    @SuppressWarnings("exports")
    @FXML public Label nome;

    @SuppressWarnings("exports")
    @FXML public TextField barraPesquisa;

    ArrayList<String> words = new ArrayList<>();

    @SuppressWarnings("exports")
    @FXML public Label nomeP;
    @SuppressWarnings("exports")
    @FXML public Label precoP;
    @SuppressWarnings("exports")
    @FXML public Label marcaP;
    @SuppressWarnings("exports")
    @FXML public Label nomePc;
    @SuppressWarnings("exports")
    @FXML public Label precoPc;
    @SuppressWarnings("exports")
    @FXML public Label marcaPc;
    @SuppressWarnings("exports")
    @FXML public Label sp1;
    @SuppressWarnings("exports")
    @FXML public Label sp2;
    @SuppressWarnings("exports")
    @FXML public Label sp3;
    @SuppressWarnings("exports")
    @FXML public Label sp4;
    @SuppressWarnings("exports")
    @FXML public Label sp5;
    @SuppressWarnings("exports")
    @FXML public Label sp6;
    @SuppressWarnings("exports")
    @FXML public Label spc1;
    @SuppressWarnings("exports")
    @FXML public Label spc2;
    @SuppressWarnings("exports")
    @FXML public Label spc3;
    @SuppressWarnings("exports")
    @FXML public Label spc4;
    @SuppressWarnings("exports")
    @FXML public Label spc5;
    @SuppressWarnings("exports")
    @FXML public Label spc6;

    @FXML private TableView<Produtos> tableView;

    @FXML private TableColumn<Produtos, String> nomeColumn;
    @FXML private TableColumn<Produtos, String> precoColumn;

    @FXML private ImageView imagem;






    @SuppressWarnings({ "exports", "rawtypes" })
    @FXML public ChoiceBox carrinho;


    public ObservableList<String> PedidosCarrinho;

    @SuppressWarnings("exports")
    @FXML public Label total;
    @SuppressWarnings("exports")
    @FXML public Label error;
    



    @SuppressWarnings("unchecked")
    @FXML
    public void initialize() {
        nome.setText(cliente.getNome());
        saldo.setText(String.valueOf(cliente.getSaldo()));

        for(int i = 0; i < Caixa.getEstoque().getProdutosNoEstoque().size(); i++){
            if(Caixa.getEstoque().getProdutosNoEstoque().get(i).getClasse().equals("Tv")){
                produtosCopy.add(new Tv((Tv)Caixa.getEstoque().getProdutosNoEstoque().get(i)));
                quantidadeCopy.add(Caixa.getEstoque().getQuantDoProduto().get(i));
            } else if(Caixa.getEstoque().getProdutosNoEstoque().get(i).getClasse().equals("Celular")){
                produtosCopy.add(new Celular((Celular)Caixa.getEstoque().getProdutosNoEstoque().get(i)));
                quantidadeCopy.add(Caixa.getEstoque().getQuantDoProduto().get(i));
            } else if(Caixa.getEstoque().getProdutosNoEstoque().get(i).getClasse().equals("Tablet")){
                produtosCopy.add(new Tablet((Tablet)Caixa.getEstoque().getProdutosNoEstoque().get(i)));
                quantidadeCopy.add(Caixa.getEstoque().getQuantDoProduto().get(i));
            }
            words.add(Caixa.getEstoque().getProdutosNoEstoque().get(i).getNome());
        }


        nomeColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("nome"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<Produtos, String>("precoVenda"));

        //load dummy data
        tableView.setItems(getProdutos());

        tableView.setEditable(false);


        nomeP.setVisible(false);
        precoP.setVisible(false);
        marcaP.setVisible(false);
        nomePc.setVisible(false);
        precoPc.setVisible(false);
        marcaPc.setVisible(false);

        sp1.setVisible(false);
        sp2.setVisible(false);
        sp3.setVisible(false);
        sp4.setVisible(false);
        sp5.setVisible(false);
        sp6.setVisible(false);
        spc1.setVisible(false);
        spc2.setVisible(false);
        spc3.setVisible(false);
        spc4.setVisible(false);
        spc5.setVisible(false);
        spc6.setVisible(false);

        imagem.setVisible(false);

        error.setVisible(false);


        stringCompra = lerCarrinhoSalvo();
        PedidosCarrinho = FXCollections.observableArrayList(stringCompra);
        carrinho.setItems(PedidosCarrinho);


        if(cliente.getCarrinho() == null){
            List<Produtos> produtos = new ArrayList<>();
            List<Integer> quantidade = new ArrayList<>();
            cliente.setCarrinho(new Compra(produtos, quantidade, cliente.getDescontoEspecial()));
        }
        total.setText(String.valueOf(cliente.getCarrinho().getValorPago()));




    }

    private List<String> lerCarrinhoSalvo(){
        List<String> aux = new ArrayList<>();
        if(cliente.getCarrinho() != null){
            for(int i = 0; i < cliente.getCarrinho().getProdutos().size(); i++){
                aux.add(cliente.getCarrinho().getProdutos().get(i).getNome() + ", quant: " + cliente.getCarrinho().getQuantidade().get(i));
            }
        }
        return aux;
    }

    @FXML
    void resetTableview() {
        if(barraPesquisa.getText().equals("")){
            tableView.setItems(getProdutos());
        }
    }

    @FXML
    void search(ActionEvent event) {
        if(barraPesquisa.getText().equals("")){
            tableView.setItems(getProdutos());
        } else{
            tableView.setItems(getProdutosPesquisa(searchListProduct(barraPesquisa.getText(), words)));
        }
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    private List<Produtos> searchListProduct(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = searchList(searchWords, listOfStrings);
        List<Produtos> searchProdutosArray = new ArrayList<>();
        for(int i = 0; i < produtosCopy.size(); i++){
            for(int j = 0; j < searchWordsArray.size(); j++){
                if(produtosCopy.get(i).getNome().equals(searchWordsArray.get(j))){
                    searchProdutosArray.add(produtosCopy.get(i));
                }
            }
        }
        return searchProdutosArray;


    }



    @FXML
    private void mostrarInfo(){
        Produtos produtoSelecionado =  tableView.getSelectionModel().getSelectedItem();
        if(produtoSelecionado != null){
            nomeP.setVisible(true);
            precoP.setVisible(true);
            marcaP.setVisible(true);
            nomePc.setText(produtoSelecionado.getNome());
            precoPc.setText(String.valueOf(produtoSelecionado.getPrecoVenda()));
            marcaPc.setText(produtoSelecionado.getMarca());
            nomePc.setVisible(true);
            precoPc.setVisible(true);
            marcaPc.setVisible(true);

            try (FileInputStream inputstream = new FileInputStream(produtoSelecionado.getPath())) {
                Image image = new Image(inputstream);
                imagem.setImage(image);
                imagem.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(produtoSelecionado.getClasse().equals("Tv")){
                sp1.setText("Tamanho Da Tela (pol): ");
                sp2.setText("Resolução da Tela: ");
                sp3.setText("Tipo de Tela: ");
                sp4.setText("É smart: ");
                sp5.setText("Acompanha suporte: ");
                sp6.setText("Consumo de Energia (kWh): ");
                sp1.setVisible(true);
                sp2.setVisible(true);
                sp3.setVisible(true);
                sp4.setVisible(true);
                sp5.setVisible(true);
                sp6.setVisible(true);
                spc1.setText(String.valueOf(((Tv) (produtoSelecionado)).getTamanhoTela()));
                spc2.setText(((Tv) (produtoSelecionado)).getResolucaoTela());
                spc3.setText(((Tv) (produtoSelecionado)).getTipoTela());
                spc4.setText(boolToString(((Tv) (produtoSelecionado)).isSmart()));
                spc5.setText(boolToString(((Tv) (produtoSelecionado)).isSuport()));
                spc6.setText(String.valueOf(((Tv) (produtoSelecionado)).getConsumoEnergia()));
                spc1.setVisible(true);
                spc2.setVisible(true);
                spc3.setVisible(true);
                spc4.setVisible(true);
                spc5.setVisible(true);
                spc6.setVisible(true);
            } else if(produtoSelecionado.getClasse().equals("Celular")){
                sp1.setText("Tamanho Da Tela (pol): ");
                sp2.setText("Quantidade de Chips Suportados: ");
                sp3.setText("Armazenamento (GB): ");
                sp4.setText("Resolução da Câmera (MP): ");
                sp5.setText("Capacidade da Bateria (mAh) : ");
                sp6.setText("Quais as Resistências do Aparelho: ");
                sp1.setVisible(true);
                sp2.setVisible(true);
                sp3.setVisible(true);
                sp4.setVisible(true);
                sp5.setVisible(true);
                sp6.setVisible(true);
                spc1.setText(String.valueOf(((Celular) (produtoSelecionado)).getTamanhoTela()));
                spc2.setText(String.valueOf(((Celular) (produtoSelecionado)).getQtdChips()));
                spc3.setText(String.valueOf(((Celular) (produtoSelecionado)).getArmazenamento()));
                spc4.setText(String.valueOf(((Celular) (produtoSelecionado)).getResolucaoCamera()));
                spc5.setText(String.valueOf(((Celular) (produtoSelecionado)).getCapacidadeBateria()));
                spc6.setText(((Celular) (produtoSelecionado)).getResistencias());
                spc1.setVisible(true);
                spc2.setVisible(true);
                spc3.setVisible(true);
                spc4.setVisible(true);
                spc5.setVisible(true);
                spc6.setVisible(true);

            } else if(produtoSelecionado.getClasse().equals("Tablet")){
                sp1.setText("Tamanho Da Tela (pol): ");
                sp2.setText("Armazenamento (GB): ");
                sp3.setText("Resolução da Câmera (MP): ");
                sp4.setText("Capacidade da Bateria (mAh) : ");
                sp5.setText("Acompanha Caneta: ");
                sp6.setText("Acompanha teclado: ");
                sp1.setVisible(true);
                sp2.setVisible(true);
                sp3.setVisible(true);
                sp4.setVisible(true);
                sp5.setVisible(true);
                sp6.setVisible(true);
                spc1.setText(String.valueOf(((Tablet) (produtoSelecionado)).getTamanhoTela()));
                spc2.setText(String.valueOf(((Tablet) (produtoSelecionado)).getArmazenamento()));
                spc3.setText(String.valueOf(((Tablet) (produtoSelecionado)).getResolucaoCamera()));
                spc4.setText(String.valueOf(((Tablet) (produtoSelecionado)).getCapacidadeBateria()));
                spc5.setText(boolToString(((Tablet) (produtoSelecionado)).isCaneta()));
                spc5.setText(boolToString(((Tablet) (produtoSelecionado)).isTeclado()));
                spc1.setVisible(true);
                spc2.setVisible(true);
                spc3.setVisible(true);
                spc4.setVisible(true);
                spc5.setVisible(true);
                spc6.setVisible(true);
            }
            
        }
    }


    private String boolToString(boolean bool){
        if(bool) return "Sim";
        else return "Não";
    }


    @SuppressWarnings("unchecked")
    @FXML
    void comprar(ActionEvent event) throws Exception {
        if(cliente.getCarrinho() == null){
            List<Produtos> produtos = new ArrayList<>();
            List<Integer> quantidade = new ArrayList<>();
            cliente.setCarrinho(new Compra(produtos, quantidade, cliente.getDescontoEspecial()));
        }
        Produtos produtoSelecionado =  tableView.getSelectionModel().getSelectedItem();
        if(produtoSelecionado != null){

            adicionarACompra(produtoSelecionado);
            removerDeProdutos(produtoSelecionado);
            tableView.setItems(getProdutos());

            PedidosCarrinho = FXCollections.observableArrayList(stringCompra);
            carrinho.setItems(PedidosCarrinho);


            total.setText(String.valueOf(cliente.getCarrinho().getValorPago()));

        }
        escrArquivo.salvar(Salvar);
    }

    @SuppressWarnings("unchecked")
    @FXML
    void retirarDoCarrinho(ActionEvent event) throws Exception {
        if(carrinho.getValue() != null){
            for(int i = 0; i < this.stringCompra.size(); i++){
                if(this.stringCompra.get(i).equals(carrinho.getValue())){
                    adicionarAProdutos(cliente.getCarrinho().getProdutos().get(i));
                    removerDaCompra(cliente.getCarrinho().getProdutos().get(i));
                }
            }

            tableView.setItems(getProdutos());
            PedidosCarrinho = FXCollections.observableArrayList(stringCompra);
            carrinho.setItems(PedidosCarrinho);

            if(cliente.getCarrinho() == null){
                total.setText("0");
            } else{
                total.setText(String.valueOf(cliente.getCarrinho().getValorPago()));
            }
            escrArquivo.salvar(Salvar);

        }
    }

    @SuppressWarnings("unchecked")
    @FXML
    void realizarPedido(ActionEvent event) throws Exception {
        error.setVisible(false);
        if(cliente.getCarrinho() != null){
            if(cliente.getCarrinho().getProdutos().size() == 0){
                error.setText("Carrinho vazio");
                error.setVisible(true);
            } else if(cliente.comprar()){
                saldo.setText(String.valueOf(cliente.getSaldo()));
                stringCompra = lerCarrinhoSalvo();
                PedidosCarrinho = FXCollections.observableArrayList(stringCompra);
                carrinho.setItems(PedidosCarrinho);
                if(cliente.getCarrinho() == null){
                    List<Produtos> produtos = new ArrayList<>();
                    List<Integer> quantidade = new ArrayList<>();
                    cliente.setCarrinho(new Compra(produtos, quantidade, cliente.getDescontoEspecial()));
                }
                escrArquivo.salvar(Salvar);
                total.setText(String.valueOf(cliente.getCarrinho().getValorPago()));
                
                error.setText("Pedido realizado!");
                error.setVisible(true);
            } else{
                error.setText("Saldo insuficiente");
                error.setVisible(true);
            }
        }
    }

    private Produtos DeepCopyProduto(Produtos produto){
        if(produto.getClasse().equals("Celular")){
            return new Celular((Celular) produto);
        } else if(produto.getClasse().equals("Tv")){
            return new Tv((Tv) produto);
        } else if(produto.getClasse().equals("Tablet")){
            return new Tablet((Tablet) produto);
        } else{
            return null;
        }
    }


    @SuppressWarnings("deprecation")
    public List<Integer> deepCopyInteger(List<Integer> listInteger){
        List<Integer> copy = new ArrayList<>();
        for(int i = 0; i<listInteger.size(); i++){
            copy.add(new Integer(listInteger.get(i)));
        }
        return copy;
    }







    private ObservableList<Produtos> getProdutos(){
        ObservableList<Produtos> produtos = FXCollections.observableArrayList();

        for(int i = 0; i<this.produtosCopy.size(); i++){
            produtos.add(this.produtosCopy.get(i));
        }
        return produtos;
    }


    private ObservableList<Produtos> getProdutosPesquisa(List<Produtos> listaProd){
        ObservableList<Produtos> produtos = FXCollections.observableArrayList();

        for(int i = 0; i<listaProd.size(); i++){
            produtos.add(listaProd.get(i));
        }
        return produtos;
    }


    private boolean adicionarAProdutos(Produtos produto){
        for(int i = 0; i < this.produtosCopy.size(); i++){
            if(produto.getNome().equals(this.produtosCopy.get(i).getNome())){
                this.quantidadeCopy.set(i, this.quantidadeCopy.get(i) + 1);
                return true;
            }
        }

        this.produtosCopy.add(produto);
        this.quantidadeCopy.add(1);
        return true;

    }


    public boolean removerDeProdutos(Produtos produto){
        for(int i = 0; i < this.produtosCopy.size(); i++){
            if(produto.getNome().equals(this.produtosCopy.get(i).getNome())){
                if(this.quantidadeCopy.get(i) == 1){
                    this.quantidadeCopy.remove(i);
                    this.produtosCopy.remove(i);
                } else{
                    this.quantidadeCopy.set(i, this.quantidadeCopy.get(i) - 1);
                }
                return true;
            }
        }
        return false;
    }

    private boolean adicionarACompra(Produtos produto){
        if(cliente.getCarrinho() == null){
            List<Produtos> produtos = new ArrayList<>();
            List<Integer> quantidade = new ArrayList<>();
            cliente.setCarrinho(new Compra(produtos, quantidade, cliente.getDescontoEspecial()));
        }
        for(int i = 0; i < cliente.getCarrinho().getProdutos().size(); i++){
            if(produto.getNome().equals(cliente.getCarrinho().getProdutos().get(i).getNome())){
                cliente.adicionarAoCarrinho(DeepCopyProduto(produto), 1);
                this.stringCompra.set(i, produto.getNome() + ", quant: " + cliente.getCarrinho().getQuantidade().get(i));
                return true;
            }
        }
        cliente.adicionarAoCarrinho(DeepCopyProduto(produto), 1);
        this.stringCompra.add(produto.getNome() + ", quant: " + 1);
        return true;

    }


    private boolean removerDaCompra(Produtos produto){

        for(int i = 0; i < cliente.getCarrinho().getProdutos().size(); i++){
            if(produto.getNome().equals(cliente.getCarrinho().getProdutos().get(i).getNome())){
                if(cliente.getCarrinho().getQuantidade().get(i) == 1){
                    cliente.removerDoCarrinho(produto, 1);
                    this.stringCompra.remove(i);
                } else{
                    cliente.removerDoCarrinho(produto, 1);
                    this.stringCompra.set(i, produto.getNome() + ", quant: " + cliente.getCarrinho().getQuantidade().get(i));
                }
                return true;
            }
        }
        return false;
    }



    @FXML
    private void switchAdicionarSaldo(ActionEvent event) throws IOException {
        AdicionarSaldoController.Salvar = Salvar;
        AdicionarSaldoController.caixa = caixa;
        AdicionarSaldoController.cliente = cliente;
        root = FXMLLoader.load(getClass().getResource("AdicionarSaldo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
