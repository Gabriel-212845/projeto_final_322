package class01;

import java.util.List;
import java.time.LocalDate;


public class PedidoDeEstoque {
    private Gerente gerenteResponsavel;
    private List<Produtos> produtos;
    private List<Integer> quantidade;
    private LocalDate data;
    private double valorTotal;

    public LocalDate getData() {
        return data;
    }
    public List<Produtos> getProdutos() {
        return produtos;
    }
    public List<Integer> getQuantidade() {
        return quantidade;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public Gerente getGerenteResponsavel() {
        return gerenteResponsavel;
    }


    PedidoDeEstoque(Gerente gerenteResponsavel, List<Produtos> produtos, List<Integer> quantidade){
        this.gerenteResponsavel = gerenteResponsavel;
        this.produtos = produtos;
        this.quantidade = quantidade;
        this.data = LocalDate.now();
        double aux = 0;
        for(int i=0; i<=produtos.size(); i++){
            aux += produtos.get(i).getPrecoCusto() * quantidade.get(i);
        }
        this.valorTotal = aux;
    }

}
