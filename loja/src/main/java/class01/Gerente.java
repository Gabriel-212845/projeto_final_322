package class01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gerente extends Pessoas implements Gerir{
    private static double cofre;
    private double lucro;
    private List<Caixa> caixasContratados;
    private List<Double> historicoDeRendimentos;

    public static double getCofre() {
        return cofre;
    }
    public double getLucro() {
        return lucro;
    }
    public List<Caixa> getCaixasContratados() {
        return caixasContratados;
    }
    public List<Double> getHistoricoDeRendimentos() {
        return historicoDeRendimentos;
    }
    public String getClasse() {
        return "Gerente";
    }



    public void setCaixasContratados(List<Caixa> caixasContratados) {
        this.caixasContratados = caixasContratados;
    }

    public static void setCofre(double cofrre){
        cofre = cofrre;
    }


    public void colocarNoCofre(double valorGanho){
        cofre = cofre + valorGanho;
    }

    public void gastarDoCofre(double valorGasto){
        cofre = cofre - valorGasto;
    }

    public void contratarCaixa(Caixa caixa){
        caixa.setDataDeContratacao(LocalDate.now());
        caixasContratados.add(caixa);
    }

    public void addCaixaCont(Caixa caixa){
        if(caixa != null) this.caixasContratados.add(caixa);
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

        int aux = -1;
        for(int i=0; i < this.caixasContratados.size();i++){
            if(this.caixasContratados.get(i).getId().equals(caixa.getId())){
                aux = i;
                break;
            }
        }
        if(aux>=0){
            this.caixasContratados.get(aux).adicionarPedidoDeEstoque(pedido);
            return true;
        }
        return false;
    }





    @Override
    public String toString() {
        String aux = "Gerente \n";
        aux += nome + id + descontoEspecial + saldo + "\n" + carrinho + "\n" +comprasAguardandoPostagem + "\n" +comprasEmTransito+"\n" +
               historicoDeCompras+"\n" + Gerente.getCofre() +"\n"+ lucro +"\n"+ caixasContratados +"\n"+ historicoDeRendimentos+"\n";
        return aux;
    }




    public Gerente(String nome, String id, double saldo, double lucro) {
        super(nome, id, saldo);
        this.lucro = lucro;
        this.descontoEspecial = 0.1;
        this.caixasContratados = new ArrayList<>();
        this.historicoDeRendimentos = new ArrayList<>();
    }
    public Gerente(String nome, String id, double descontoEspecial, double saldo, Compra carrinho, List<Compra> comprasAguardandoPostagem,
                 List<Compra> comprasEmTransito, List<Compra> historicoDeCompras, double cofre, double lucro, List<Caixa> caixasContratados,
                 List<Double> historicoDeRendimentos){
        super(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito, historicoDeCompras);
        Gerente.setCofre(cofre);
        this.lucro = lucro;
        this.caixasContratados = caixasContratados;
        this.historicoDeRendimentos = historicoDeRendimentos;
    }
    public Gerente(String nome, String id, double descontoEspecial, double saldo, Compra carrinho, List<Compra> comprasAguardandoPostagem,
                 List<Compra> comprasEmTransito, List<Compra> historicoDeCompras, double cofre, double lucro,
                 List<Double> historicoDeRendimentos){
        super(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito, historicoDeCompras);
        Gerente.setCofre(cofre);
        this.lucro = lucro;
        this.caixasContratados = new ArrayList<>();
        this.historicoDeRendimentos = historicoDeRendimentos;
    }


}
