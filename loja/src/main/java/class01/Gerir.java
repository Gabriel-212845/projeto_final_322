package class01;

import java.time.LocalDate;
import java.util.List;

public interface Gerir {
    void contratarCaixa(Caixa caixa);
    void demitirCaixa(Caixa caixa);
    void invormacoesDoCaixa(Caixa caixa);
    void analisarPedidosDeAumento();
    double calcularDespesaDeFuncionarios(double RendimentoDoMes);
    double calcularDespesaDeProduto(LocalDate data);
    double calcularRendimentoDoMes(LocalDate data);
    void pagarDispesas(LocalDate data);
    boolean solicitarCompra(List<Produtos> produtos, List<Integer> quant, Caixa caixa);

}
