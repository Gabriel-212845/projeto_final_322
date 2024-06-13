package class01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gerente extends Pessoas implements Gerir{
    private static double cofre;
    private double lucro;
    private List<Caixa> caixasContratados;
    private List<Integer> historicoDeRendimentos;

    public static double getCofre() {
        return cofre;
    }
    public double getLucro() {
        return lucro;
    }
    public List<Caixa> getCaixasContratados() {
        return caixasContratados;
    }
    public List<Integer> getHistoricoDeRendimentos() {
        return historicoDeRendimentos;
    }



    public void colocarNoCofre(double valorGanho){
        cofre = cofre + valorGanho;
    }

    public void gastarDoCofre(double valorGasto){
        cofre = cofre - valorGasto;
    }

    public void contratarCaixa(Caixa caixa){
        caixasContratados.add(caixa);
    }

    public void demitirCaixa(Caixa caixa){
        if(caixasContratados.remove(caixa)){
            double multa = 2 * caixa.getSalario();
            gastarDoCofre(multa);
        }
    }

    public void invormacoesDoCaixa(Caixa caixa){
        System.out.println(caixa);
    }

    public void analisarPedidosDeAumento(){
        for(int i = 0; i<this.caixasContratados.size(); i++){
            if(this.caixasContratados.get(i).getPedidoDeAumento() != null){
                LocalDate data = this.caixasContratados.get(i).getPedidoDeAumento().getData();
                data.minusYears(1);
                if(this.caixasContratados.get(i).getDataDeContratacao().isBefore(data)){
                    this.caixasContratados.get(i).setSalario(this.caixasContratados.get(i).getSalario()*1.2);
                    this.caixasContratados.get(i).adicionarAumentoNoHistorico(this.caixasContratados.get(i).getPedidoDeAumento());
                }

                this.caixasContratados.get(i).setPedidoDeAumento(null);
            }
        }
    }











    public double calcularDespesaDeFuncionarios(double RendimentoDoMes){
        double valor = 0;
        for(int i=0; i<+caixasContratados.size(); i++){
            valor += caixasContratados.get(i).getSalario();
        }
        valor += RendimentoDoMes * lucro;
        return valor;
    }

    public double calcularDespesaDeProduto(LocalDate data){
        LocalDate mesPassado = data.minusMonths(1);
        double valor = 0;
        for(int i=0; i<caixasContratados.size(); i++){
            for(int j=0; j<caixasContratados.get(i).getHistoricoDePedidos().size(); j++){
                if(caixasContratados.get(i).getHistoricoDePedidos().get(j).getData().isAfter(mesPassado)){
                    valor += caixasContratados.get(i).getHistoricoDePedidos().get(j).getValorTotal();
                }
            }
        }
        return valor;
    }

    public double calcularRendimentoDoMes(LocalDate data){
        LocalDate mesPassado = data.minusMonths(1);
        double rendimento = 0;
        for(int i=0; i<caixasContratados.size(); i++){
            for(int j=0; j<caixasContratados.get(i).getClientes().size(); j++){
                for(int k=0; k<caixasContratados.get(i).getClientes().get(j).getHistoricoDeCompras().size(); k++){

                    List<Compra> comprasFeitas = caixasContratados.get(i).getClientes().get(j).getHistoricoDeCompras();
                    if(comprasFeitas.get(k).getDataDoPagamento().isAfter(mesPassado)){
                        rendimento += comprasFeitas.get(k).getValorPago();
                    }
                }
            }
        }
        return rendimento;
    }

    public void pagarDispesas(LocalDate data){
        double rendimento = calcularRendimentoDoMes(data);
        double despesasTotais = calcularDespesaDeFuncionarios(rendimento) + calcularDespesaDeProduto(data);
        colocarNoCofre(rendimento);
        gastarDoCofre(despesasTotais);
        System.out.println("Há R$"+cofre+"no cofre.\n");
    }

    public boolean solicitarCompra(List<Produtos> produtos, List<Integer> quant, Caixa caixa){
        PedidoDeEstoque pedido = new PedidoDeEstoque(this, produtos, quant);
        for(int i=0; i<this.caixasContratados.size();i++){
            if(caixasContratados.get(i).equals(caixa)){
                caixasContratados.get(i).adicionarPedidoDeEstoque(pedido);
                return true;
            }
        }
        return false;
    }






    Gerente(String nome, String id, double lucro) {
        super(nome, id);
        this.lucro = lucro;
        this.descontoEspecial = 0.1;
        this.caixasContratados = new ArrayList<>();
        this.historicoDeRendimentos = new ArrayList<>();
    }


}
