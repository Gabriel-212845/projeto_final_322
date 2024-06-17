package class01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract public class Pessoas {
    protected String nome;
    protected String id;
    protected double descontoEspecial;
    protected double saldo;
    protected Compra carrinho;
    protected List<Compra> comprasAguardandoPostagem;
    protected List<Compra> comprasEmTransito;
    protected List<Compra> historicoDeCompras;

    public String getNome() {
        return nome;
    }
    public String getId() {
        return id;
    }
    public Compra getCarrinho() {
        return carrinho;
    }
    public List<Compra> getComprasAguardandoPostagem() {
        return comprasAguardandoPostagem;
    }
    public List<Compra> getComprasEmTransito() {
        return comprasEmTransito;
    }
    public double getDescontoEspecial() {
        return descontoEspecial;
    }
    public List<Compra> getHistoricoDeCompras() {
        return historicoDeCompras;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getClasse() {
        return "Pessoas";
    }
    

    public void adicionarAoCarrinho(Produtos produto, int quant){
        if(carrinho == null){
            List<Produtos> produtos = new ArrayList<>();
            produtos.add(produto);
            List<Integer> quantidade = new ArrayList<>();
            quantidade.add(quant);
            this.carrinho = new Compra(produtos, quantidade, descontoEspecial);
        } else{
            for(int i = 1; i <= quant; i++){
                this.carrinho.adicionarACompra(produto);
            }
        }
    }

    public void removerDoCarrinho(Produtos produto, int quant){
        if(this.carrinho != null){
            for(int i = 1; i <= quant; i++){
                if(this.carrinho.getProdutos().contains(produto)){
                    this.carrinho.removerDaCompra(produto);
                }
            }
        }
    }


    public boolean comprar(){
        //verificação de pagamento
        this.carrinho.setDataDoPagamento(LocalDate.now());
        this.comprasAguardandoPostagem.add(this.carrinho);
        this.carrinho = null;


        return true;

    }







    public Pessoas(String nome, String id, double saldo){
        this.nome = nome;
        this.id = id;
        this.saldo = saldo;
        this.carrinho = null;
        this.comprasAguardandoPostagem = new ArrayList<>();
        this.comprasEmTransito = new ArrayList<>();
        this.historicoDeCompras = new ArrayList<>();

    }

    public Pessoas(String nome, String id, double descontoEspecial, double saldo, Compra carrinho, List<Compra> comprasAguardandoPostagem,
                   List<Compra> comprasEmTransito, List<Compra> historicoDeCompras){
        this.nome = nome;
        this.id = id;
        this.descontoEspecial = descontoEspecial;
        this.saldo = saldo;
        this.carrinho = carrinho;
        this.comprasAguardandoPostagem = comprasAguardandoPostagem;
        this.comprasEmTransito = comprasEmTransito;
        this.historicoDeCompras = historicoDeCompras;

    }


}
