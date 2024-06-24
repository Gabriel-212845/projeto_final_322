package class01;

import java.util.ArrayList;
import java.util.List;

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
    public String getPath() {
        return path;
    }

    public String getClasse(){
        return "Produtos";
    }

    public static List<Produtos> DeepCopyList(List<Produtos> listaProdutos){
        List<Produtos> copy = new ArrayList<>();
        for(int i = 0; i< listaProdutos.size(); i++){
            if(listaProdutos.get(i).getClasse().equals("Tv")){
                copy.add(new Tv((Tv)listaProdutos.get(i)));
            } else if(listaProdutos.get(i).getClasse().equals("Celular")){
                copy.add(new Celular((Celular)listaProdutos.get(i)));
            } else if(listaProdutos.get(i).getClasse().equals("Tablet")){
                copy.add(new Tablet((Tablet)listaProdutos.get(i)));
            }
        }
        return copy;
    }

    @Override
    public Produtos clone() throws CloneNotSupportedException {
        return (Produtos) super.clone();
    }

    public abstract void mostrarDetalhes();
}
