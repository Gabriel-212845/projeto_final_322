package class01;

public class Celular extends Produtos {
    private double tamanhoTela;
    private int qtdChips;
    private int armazenamento;
    private int resolucaoCamera;
    private int capacidadeBateria;
    private String resistencias;

    public Celular(double precoCusto, double precoVenda, String nome, String marca, String fornecedor, boolean promocao, String path,
                   double tamanhoTela, int qtdChips, int armazenamento, int resolucaoCamera, int capacidadeBateria, String resistencias) {
        super(precoCusto, precoVenda, nome, marca, fornecedor, promocao, path);
        this.tamanhoTela = tamanhoTela;
        this.qtdChips = qtdChips;
        this.armazenamento = armazenamento;
        this.resolucaoCamera = resolucaoCamera;
        this.capacidadeBateria = capacidadeBateria;
        this.resistencias = resistencias;
    }
    public Celular(Celular celular) {
        super(celular.precoCusto, celular.precoVenda, celular.nome, celular.marca, celular.fornecedor, celular.promocao, celular.path);
        this.tamanhoTela = celular.tamanhoTela;
        this.qtdChips = celular.qtdChips;
        this.armazenamento = celular.armazenamento;
        this.resolucaoCamera = celular.resolucaoCamera;
        this.capacidadeBateria = celular.capacidadeBateria;
        this.resistencias = celular.resistencias;
    }

    public double getTamanhoTela() {
        return tamanhoTela;
    }

    public void setTamanhoTela(double tamanhoTela) {
        this.tamanhoTela = tamanhoTela;
    }

    public int getQtdChips() {
        return qtdChips;
    }

    public void setQtdChips(int qtdChips) {
        this.qtdChips = qtdChips;
    }

    public int getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(int armazenamento) {
        this.armazenamento = armazenamento;
    }

    public int getResolucaoCamera() {
        return resolucaoCamera;
    }

    public void setResolucaoCamera(int resolucaoCamera) {
        this.resolucaoCamera = resolucaoCamera;
    }

    public int getCapacidadeBateria() {
        return capacidadeBateria;
    }

    public void setCapacidadeBateria(int capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    public String getResistencias() {
        return resistencias;
    }

    public void setResistencias(String resistencias) {
        this.resistencias = resistencias;
    }


    public String getClasse(){
        return "Celular";
    }

    @Override
    public String toString() {
        return "Celular: " + nome + "\nMarca: " + marca + "\nPreço de Venda: " + precoVenda + "\n Preço de Custo: " + precoCusto +
               "\nFornecedor: " + fornecedor + "\nEm promoção: " + promocao + "\nTamanho da Tela: " + tamanhoTela +
               "\nQuantidade de Chips: " + qtdChips + "\nArmazenamento: " + armazenamento + "GB \nResolução da Câmera: " +
               resolucaoCamera + "MP" +
               "\nCapacidade da Bateria: " + capacidadeBateria + "mAh\nResistências: " + resistencias;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Celular: " + nome + ", Marca: " + marca + ", Preço de Venda: " + precoVenda + ", Preço de Custo: " + precoCusto);
        System.out.println("Fornecedor: " + fornecedor + ", Em promoção: " + promocao);
        System.out.println("Tamanho da Tela: " + tamanhoTela + ", Quantidade de Chips: " + qtdChips);
        System.out.println("Armazenamento: " + armazenamento + "GB, Resolução da Câmera: " + resolucaoCamera + "MP");
        System.out.println("Capacidade da Bateria: " + capacidadeBateria + "mAh, Resistências: " + resistencias);
    }

    @Override
    public Celular clone() throws CloneNotSupportedException {
        return (Celular) super.clone();
    }
}
