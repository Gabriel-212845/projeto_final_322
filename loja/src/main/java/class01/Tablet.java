package class01;

public class Tablet extends Produtos {
    private double tamanhoTela;
    private int armazenamento;
    private int resolucaoCamera;
    private int capacidadeBateria;
    private boolean caneta;
    private boolean teclado;

    public Tablet(double precoCusto, double precoVenda, String nome, String marca, String fornecedor, boolean promocao, String path,
                  double tamanhoTela, int armazenamento, int resolucaoCamera, int capacidadeBateria, boolean caneta, boolean teclado) {
        super(precoCusto, precoVenda, nome, marca, fornecedor, promocao, path);
        this.tamanhoTela = tamanhoTela;
        this.armazenamento = armazenamento;
        this.resolucaoCamera = resolucaoCamera;
        this.capacidadeBateria = capacidadeBateria;
        this.caneta = caneta;
        this.teclado = teclado;
    }
    public Tablet(Tablet tablet) {
        super(tablet.precoCusto, tablet.precoVenda, tablet.nome, tablet.marca, tablet.fornecedor, tablet.promocao, tablet.path);
        this.tamanhoTela = tablet.tamanhoTela;
        this.armazenamento = tablet.armazenamento;
        this.resolucaoCamera = tablet.resolucaoCamera;
        this.capacidadeBateria = tablet.capacidadeBateria;
        this.caneta = tablet.caneta;
        this.teclado = tablet.teclado;
    }

    public double getTamanhoTela() {
        return tamanhoTela;
    }

    public void setTamanhoTela(double tamanhoTela) {
        this.tamanhoTela = tamanhoTela;
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

    public boolean isCaneta() {
        return caneta;
    }

    public void setCaneta(boolean caneta) {
        this.caneta = caneta;
    }

    public boolean isTeclado() {
        return teclado;
    }

    public void setTeclado(boolean teclado) {
        this.teclado = teclado;
    }

    public String getClasse(){
        return "Tablet";
    }




    @Override
    public String toString() {
        return "Tablet: " + nome + "\nMarca: " + marca + "\nPreço de Venda: " + precoVenda + "\nPreço de Custo: " + precoCusto +
               "\nFornecedor: " + fornecedor + "\nEm promoção: " + promocao + "\nTamanho da Tela: " + tamanhoTela + "\nArmazenamento: " +
               armazenamento + "GB" + "\nResolução da Câmera: " + resolucaoCamera + "MP\nCapacidade da Bateria: " + capacidadeBateria +
               "mAh" + "\nCaneta: " + (caneta ? "Sim" : "Não") + "\nTeclado: " + (teclado ? "Sim" : "Não");

    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Tablet: " + nome + ", Marca: " + marca + ", Preço de Venda: " + precoVenda + ", Preço de Custo: " + precoCusto);
        System.out.println("Fornecedor: " + fornecedor + ", Em promoção: " + promocao);
        System.out.println("Tamanho da Tela: " + tamanhoTela + ", Armazenamento: " + armazenamento + "GB");
        System.out.println("Resolução da Câmera: " + resolucaoCamera + "MP, Capacidade da Bateria: " + capacidadeBateria + "mAh");
        System.out.println("Caneta: " + (caneta ? "Sim" : "Não") + ", Teclado: " + (teclado ? "Sim" : "Não"));
    }

    @Override
    public Tablet clone() throws CloneNotSupportedException {
        return (Tablet) super.clone();
    }
}
