package class01;

public class Tv extends Produtos {
    private double tamanhoTela;
    private String resolucaoTela;
    private String tipoTela;
    private boolean smart;
    private boolean suport;
    private double consumoEnergia;

    public Tv(double precoCusto, double precoVenda, String nome, String marca, String fornecedor, boolean promocao,
              double tamanhoTela, String resolucaoTela, String tipoTela, boolean smart, boolean suport, double consumoEnergia) {
        super(precoCusto, precoVenda, nome, marca, fornecedor, promocao);
        this.tamanhoTela = tamanhoTela;
        this.resolucaoTela = resolucaoTela;
        this.tipoTela = tipoTela;
        this.smart = smart;
        this.suport = suport;
        this.consumoEnergia = consumoEnergia;
    }

    public double getTamanhoTela() {
        return tamanhoTela;
    }

    public void setTamanhoTela(double tamanhoTela) {
        this.tamanhoTela = tamanhoTela;
    }

    public String getResolucaoTela() {
        return resolucaoTela;
    }

    public void setResolucaoTela(String resolucaoTela) {
        this.resolucaoTela = resolucaoTela;
    }

    public String getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(String tipoTela) {
        this.tipoTela = tipoTela;
    }

    public boolean isSmart() {
        return smart;
    }

    public void setSmart(boolean smart) {
        this.smart = smart;
    }

    public boolean isSuport() {
        return suport;
    }

    public void setSuport(boolean suport) {
        this.suport = suport;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(double consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("TV: " + nome + ", Marca: " + marca + ", Preço de Venda: " + precoVenda + ", Preço de Custo: " + precoCusto);
        System.out.println("Fornecedor: " + fornecedor + ", Em promoção: " + promocao);
        System.out.println("Tamanho da Tela: " + tamanhoTela + " polegadas, Resolução da Tela: " + resolucaoTela);
        System.out.println("Tipo de Tela: " + tipoTela + ", Smart: " + (smart ? "Sim" : "Não"));
        System.out.println("Suporte: " + (suport ? "Acompanha Suporte" : "Não Acompanha Suporte") + ", Consumo de Energia: " + consumoEnergia + " kWh");
    }
}
