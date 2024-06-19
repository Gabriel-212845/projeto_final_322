package class01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Caixa extends Pessoas implements Estocar{
    private LocalDate dataDeContratacao;
    private double salario;
    private int faltasSemJust;
    private PedidoDeAumento pedidoDeAumento;
    private List<PedidoDeAumento> historicoDeAumentos;
    private static Estoque estoque;
    private List<PedidoDeEstoque> pedidosDeEstoque;
    private List<PedidoDeEstoque> historicoDePedidos;
    private List<Pessoas> clientes;

    public LocalDate getDataDeContratacao() {
        return dataDeContratacao;
    }
    public double getSalario() {
        return salario;
    }
    public int getFaltasSemJust() {
        return faltasSemJust;
    }
    public PedidoDeAumento getPedidoDeAumento() {
        return pedidoDeAumento;
    }
    public List<PedidoDeAumento> getHistoricoDeAumentos() {
        return historicoDeAumentos;
    }
    public List<PedidoDeEstoque> getPedidosDeEstoque() {
        return pedidosDeEstoque;
    }
    public List<PedidoDeEstoque> getHistoricoDePedidos() {
        return historicoDePedidos;
    }
    public List<Pessoas> getClientes() {
        return clientes;
    }
    public String getClasse() {
        return "Caixa";
    }

    public void setPedidoDeAumento(PedidoDeAumento pedidoDeAumento) {
        this.pedidoDeAumento = pedidoDeAumento;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public void setDataDeContratacao(LocalDate dataDeContratacao) {
        this.dataDeContratacao = dataDeContratacao;
    }
    public void setClientes(List<Pessoas> clientes) {
        this.clientes = clientes;
    }
    public void setHistoricoDeAumentos(List<PedidoDeAumento> historicoDeAumentos) {
        this.historicoDeAumentos = historicoDeAumentos;
    }
    public void setPedidosDeEstoque(List<PedidoDeEstoque> pedidosDeEstoque) {
        this.pedidosDeEstoque = pedidosDeEstoque;
    }
    public void setHistoricoDePedidos(List<PedidoDeEstoque> historicoDePedidos) {
        this.historicoDePedidos = historicoDePedidos;
    }

    public static void setEstoque(Estoque esttoque){
        estoque = esttoque;
    }
    public static Estoque getEstoque(){
        return estoque;
    }



    public void adicionarCliente(Pessoas cliente){
        this.clientes.add(cliente);
    }
    public void adicionarClientes(List<Pessoas> clientes){
        this.clientes.addAll(clientes);
    }

    public void adicionarPedidoDeEstoque(PedidoDeEstoque pedidoDeEstoque){
        this.pedidosDeEstoque.add(pedidoDeEstoque);
    }

    public void adicionarAumentoNoHistorico(PedidoDeAumento aumento){
        this.historicoDeAumentos.add(aumento);
    }

    public String ListarPedidosDeEstoque(){
        String aux = "[";
        for(int i=0; i<pedidosDeEstoque.size(); i++){
            aux += pedidosDeEstoque.get(i).toString() + ", ";
        }
        aux += "]\n";
        return aux;
    }





    public List<Compra> pedidosAguardandoEnvio(){
        List<Compra> pedidosAguardandoEnvio = new ArrayList<>();
        for(int i = 0; i<this.clientes.size(); i++){
            pedidosAguardandoEnvio.addAll(clientes.get(i).getComprasAguardandoPostagem());
        }
        return pedidosAguardandoEnvio;
    }

    public void enviarPedidos(){
        List<Compra> pedidosAguardandoEnvio = new ArrayList<>();
        for(int i = 0; i<this.clientes.size(); i++){
            pedidosAguardandoEnvio.addAll(clientes.get(i).getComprasAguardandoPostagem());
            clientes.get(i).getComprasEmTransito().addAll(clientes.get(i).getComprasAguardandoPostagem());
            clientes.get(i).getComprasAguardandoPostagem().clear();
        }
        despachar(pedidosAguardandoEnvio);
    }


    private void despachar(List<Compra> compras){
        for(int i = 0; i<compras.size(); i++){
            for(int j=0; j<compras.get(i).getProdutos().size(); j++){
                for(int k=0; k<compras.get(i).getQuantidade().get(j); k++){
                    estoque.removerDoEstoque(compras.get(i).getProdutos().get(j));
                }
            }
        }
    }

    


    public void pedirAumento(){
        this.pedidoDeAumento = new PedidoDeAumento(this);
    }

    public void reporEstoque(){
        //comprar produtos para o estoque
        for(int k = 0; k<this.pedidosDeEstoque.size(); k++){
            PedidoDeEstoque pedidoDeEstoque = this.pedidosDeEstoque.get(k);
            for(int i=0; i<pedidoDeEstoque.getProdutos().size(); i++){
                for(int j=0; j<pedidoDeEstoque.getQuantidade().get(i); j++){
                    estoque.adicionarAoEstoque(pedidoDeEstoque.getProdutos().get(i));
                }
            }
            this.historicoDePedidos.add(pedidoDeEstoque);
        }
        this.pedidosDeEstoque.clear();
    }

    public String situacaoDoEstoque(){
        return estoque.toString();
    }

    @Override
    public String toString() {
        return nome+"\nId: "+id+"\nData de Contratação: "
                +dataDeContratacao+"\nSalario: "+salario+"\nFaltas sem Justificativa: "+faltasSemJust+"\n";
    }



    public Caixa(String nome, String id, double saldo, double salario){
        super(nome, id, saldo);
        this.salario = salario;
        this.descontoEspecial = 0.1;
        this.faltasSemJust = 0;
        this.historicoDeAumentos = new ArrayList<>();
        this.pedidosDeEstoque = new ArrayList<>();
        this.historicoDePedidos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }
    public Caixa(String nome, String id, double descontoEspecial, double saldo, Compra carrinho, List<Compra> comprasAguardandoPostagem,
                 List<Compra> comprasEmTransito, List<Compra> historicoDeCompras, LocalDate dataDeContratacao, double salario,
                 int faltasSemJust, PedidoDeAumento pedidoDeAumento, List<PedidoDeAumento> historicoDeAumentos, Estoque estoque,
                 List<PedidoDeEstoque> pedidosDeEstoque, List<PedidoDeEstoque> historicoDePedidos, List<Pessoas> clientes){
        super(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito, historicoDeCompras);
        this.dataDeContratacao = dataDeContratacao;
        this.salario = salario;
        this.faltasSemJust = faltasSemJust;
        this.pedidoDeAumento = pedidoDeAumento;
        this.historicoDeAumentos = historicoDeAumentos;
        Caixa.setEstoque(estoque);
        this.pedidosDeEstoque = pedidosDeEstoque;
        this.historicoDePedidos = historicoDePedidos;
        this.clientes = clientes;
    }

    public Caixa(String nome, String id, double descontoEspecial, double saldo, Compra carrinho, List<Compra> comprasAguardandoPostagem,
                 List<Compra> comprasEmTransito, List<Compra> historicoDeCompras, LocalDate dataDeContratacao, double salario,
                 int faltasSemJust, PedidoDeAumento pedidoDeAumento, List<PedidoDeAumento> historicoDeAumentos, Estoque estoque,
                 List<PedidoDeEstoque> pedidosDeEstoque, List<PedidoDeEstoque> historicoDePedidos){
        super(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito, historicoDeCompras);
        this.dataDeContratacao = dataDeContratacao;
        this.salario = salario;
        this.faltasSemJust = faltasSemJust;
        this.pedidoDeAumento = pedidoDeAumento;
        this.historicoDeAumentos = historicoDeAumentos;
        Caixa.setEstoque(estoque);
        this.pedidosDeEstoque = pedidosDeEstoque;
        this.historicoDePedidos = historicoDePedidos;
    }



}
