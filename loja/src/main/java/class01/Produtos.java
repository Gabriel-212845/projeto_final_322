package class01;

public abstract class Produtos {
    protected double precoCusto;
    protected double precoVenda;
    protected String nome;
    protected String marca;
    protected String fornecedor;
    protected boolean promocao;
    protected String path;

    public Produtos(double precoCusto, double precoVenda, String nome, String marca, String fornecedor, boolean promocao, String path) {
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.nome = nome;
        this.marca = marca;
        this.fornecedor = fornecedor;
        this.promocao = promocao;
        this.path = path;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public boolean isPromocao() {
        return promocao;
    }

    public void setPromocao(boolean promocao) {
        this.promocao = promocao;
    }

    public abstract void mostrarDetalhes();
}
