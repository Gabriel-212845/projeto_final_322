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
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }




    public boolean adicionarACompra(Produtos produto) {
        for(int i = 0; i <= this.produtos.size(); i++){
            if(produto.equals(this.produtos.get(i))){
                this.quantidade.set(i, this.quantidade.get(i) + 1);
                return true;
            }
        }
        this.produtos.add(produto);
        this.quantidade.add(1);
        return true;

    }

    public boolean removerDaCompra(Produtos produto){
        for(int i = 0; i <= this.produtos.size(); i++){
            if(produto.equals(this.produtos.get(i))){
                if(this.quantidade.get(i) == 0){
                    this.quantidade.remove(i);
                    this.produtos.remove(i);
                } else{
                    this.quantidade.set(i, this.quantidade.get(i) - 1);
                }
                return true;
            }
        }
        return false;
    }




















    Compra(List<Produtos> produtos, List<Integer> quantidade, double desconto){
        this.produtos = produtos;
        this.quantidade = quantidade;
        this.desconto = desconto;
        double aux = 0;
        for(int i=0; i<=produtos.size(); i++){
            aux = produtos.get(i).getPrecoVenda() * quantidade.get(i);
        }
        this.valorPago = aux - aux * desconto;
        this.dataDoPedido = LocalDate.now();
    }
}
