package class01;

import java.time.LocalDate;
import java.util.List;


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

    @Override
    public String toString() {
        String aux = "(";
        for(int i=0; i<this.produtos.size(); i++){
            aux += produtos.get(i).getNome() + " quantidade: " + quantidade.get(i) + " / ";
        }
        aux +=")";
        return aux;
    }


    public PedidoDeEstoque(Gerente gerenteResponsavel, List<Produtos> produtos, List<Integer> quantidade){
        this.gerenteResponsavel = gerenteResponsavel;
        this.produtos = produtos;
        this.quantidade = quantidade;
        this.data = LocalDate.now();
        double aux = 0;
        for(int i=0; i<produtos.size(); i++){
            aux += produtos.get(i).getPrecoCusto() * quantidade.get(i);
        }
        this.valorTotal = aux;
    }

}
