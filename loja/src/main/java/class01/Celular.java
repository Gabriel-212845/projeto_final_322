package class01;

import java.util.List;

public class Celular extends Produtos {
    private double tamanhoTela;
    private int qtdChips;
    private int armazenamento;
    private int resolucaoCamera;
    private int capacidadeBateria;
    private List<String> resistencias;

    public Celular(double precoCusto, double precoVenda, String nome, String marca, String fornecedor, boolean promocao,
                   double tamanhoTela, int qtdChips, int armazenamento, int resolucaoCamera, int capacidadeBateria, List<String> resistencias) {
        super(precoCusto, precoVenda, nome, marca, fornecedor, promocao);
        this.tamanhoTela = tamanhoTela;
        this.qtdChips = qtdChips;
        this.armazenamento = armazenamento;
        this.resolucaoCamera = resolucaoCamera;
        this.capacidadeBateria = capacidadeBateria;
        this.resistencias = resistencias;
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

    public List<String> getResistencias() {
        return resistencias;
    }

    public void setResistencias(List<String> resistencias) {
        this.resistencias = resistencias;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Celular: " + nome + ", Marca: " + marca + ", Preço de Venda: " + precoVenda + ", Preço de Custo: " + precoCusto);
        System.out.println("Fornecedor: " + fornecedor + ", Em promoção: " + promocao);
        System.out.println("Tamanho da Tela: " + tamanhoTela + ", Quantidade de Chips: " + qtdChips);
        System.out.println("Armazenamento: " + armazenamento + "GB, Resolução da Câmera: " + resolucaoCamera + "MP");
        System.out.println("Capacidade da Bateria: " + capacidadeBateria + "mAh, Resistências: " + resistencias);
    }
}
