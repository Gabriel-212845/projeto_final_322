package class01;

public interface Comprar {
    void adicionarAoCarrinho(Produtos produto, int quant);
    void removerDoCarrinho(Produtos produto, int quant);
    boolean comprar();
}