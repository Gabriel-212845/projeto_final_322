package class01;

import java.time.LocalDate;
import java.util.List;

public class Compra {
    private List<Produtos> produtos;
    private List<Integer> quantidade;
    private double desconto;
    private double valorPago;
    private LocalDate dataDoPedido;
    private LocalDate dataDoPagamento;

    public LocalDate getDataDoPagamento() {
        return dataDoPagamento;
    }
    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }
    public double getDesconto() {
        return desconto;
    }
    public List<Produtos> getProdutos() {
        return produtos;
    }
    public List<Integer> getQuantidade() {
        return quantidade;
    }
    public double getValorPago() {
        return valorPago;
    }
    
    public void setDataDoPagamento(LocalDate dataDoPagamento){
        this.dataDoPagamento = dataDoPagamento;
    }
    public void setDataDoPedido(LocalDate dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }




    public boolean adicionarACompra(Produtos produto) {
        for(int i = 0; i < this.produtos.size(); i++){
            if(produto.getNome().equals(this.produtos.get(i).getNome())){
                this.quantidade.set(i, this.quantidade.get(i) + 1);
                atualizarPrecoPago();
                return true;
            }
        }
        this.produtos.add(produto);
        this.quantidade.add(1);
        atualizarPrecoPago();
        return true;

    }

    public boolean removerDaCompra(Produtos produto){
        for(int i = 0; i < this.produtos.size(); i++){
            if(produto.getNome().equals(this.produtos.get(i).getNome())){
                if(this.quantidade.get(i) == 1){
                    this.quantidade.remove(i);
                    this.produtos.remove(i);
                } else{
                    this.quantidade.set(i, this.quantidade.get(i) - 1);
                }
                atualizarPrecoPago();
                return true;
            }
        }
        return false;
    }

    private void atualizarPrecoPago(){
        double aux = 0;
        for(int i=0; i< this.produtos.size(); i++){
            aux += this.produtos.get(i).getPrecoVenda() * this.quantidade.get(i);
        }
        this.valorPago = aux - aux * desconto;
    }




















    public Compra(List<Produtos> produtos, List<Integer> quantidade, double desconto){
        this.produtos = produtos;
        this.quantidade = quantidade;
        this.desconto = desconto;
        double aux = 0;
        for(int i=0; i<produtos.size(); i++){
            aux += produtos.get(i).getPrecoVenda() * quantidade.get(i);
        }
        this.valorPago = aux - aux * desconto;
        this.dataDoPedido = LocalDate.now();
    }

    public Compra(List<Produtos> produtos, List<Integer> quantidade, double desconto, double valorPago, LocalDate dataDoPedido,
                  LocalDate dataDoPagamento){
        this.produtos = produtos;
        this.quantidade = quantidade;
        this.desconto = desconto;
        this.valorPago = valorPago;
        this.dataDoPagamento = dataDoPagamento;
        this.dataDoPedido = dataDoPedido;
    }

    public Compra(Compra compra){
        this.produtos = compra.produtos;
        this.quantidade = compra.quantidade;
        this.desconto = compra.desconto;
        this.valorPago = compra.valorPago;
        this.dataDoPagamento = compra.dataDoPagamento;
        this.dataDoPedido = compra.dataDoPedido;
    }
}
