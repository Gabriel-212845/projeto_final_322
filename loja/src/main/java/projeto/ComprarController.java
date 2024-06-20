package projeto;

import java.util.ArrayList;
import java.util.List;

import class01.Caixa;
import class01.Celular;
import class01.Pessoas;
import class01.Produtos;
import class01.Tablet;
import class01.Tv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @SuppressWarnings("exports")
    public static Caixa caixa;
    @SuppressWarnings("exports")
    public static Pessoas cliente;
    @SuppressWarnings("exports")
    @FXML public Label saldo;
    @SuppressWarnings("exports")
    @FXML public Label nome;

    @SuppressWarnings("exports")
    @FXML public TextField barraPesquisa;

    @SuppressWarnings("exports")
    @FXML public Label nomeP;
    @SuppressWarnings("exports")
    @FXML public Label precoP;
    @SuppressWarnings("exports")
    @FXML public Label marcaP;
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

    @FXML private ImageView imagem;






    @SuppressWarnings({ "exports", "rawtypes" })
    @FXML public ChoiceBox carrinho;
    



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
        }

        //load dummy data
        tableView.setItems(getProdutos());

        tableView.setEditable(false);

    }

    @FXML
    private ObservableList<Produtos> getProdutos(){
        ObservableList<Produtos> produtos = FXCollections.observableArrayList();

        for(int i = 0; i<this.produtosCopy.size(); i++){
            produtos.add(this.produtosCopy.get(i));
        }
        return produtos;
    }

    @FXML
    private boolean adicionarAProdutos(Produtos produto){
        for(int i = 0; i < this.produtosCopy.size(); i++){
            if(produto.equals(this.produtosCopy.get(i))){
                this.quantidadeCopy.set(i, this.quantidadeCopy.get(i) + 1);
                return true;
            }
        }

        this.produtosCopy.add(produto);
        this.quantidadeCopy.add(1);
        return true;

    }

    @SuppressWarnings("exports")
    @FXML
    public boolean removerDeProdutos(Produtos produto){
        for(int i = 0; i < this.produtosCopy.size(); i++){
            if(produto.equals(this.produtosCopy.get(i))){
                if(this.quantidadeCopy.get(i) == 0){
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






}
