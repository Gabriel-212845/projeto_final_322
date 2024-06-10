package class01;

import java.util.List;

public class Estoque {
    private List<Produtos> produtosNoEstoque;
    private List<Integer> quantDoProduto;

    public List<Produtos> getProdutosNoEstoque() {
        return produtosNoEstoque;
    }

    public List<Integer> getQuantDoProduto() {
        return quantDoProduto;
    }

    public boolean adicionarAoEstoque(Produtos produto) {
        for(int i = 0; i <= this.produtosNoEstoque.size(); i++){
            if(produto.equals(this.produtosNoEstoque.get(i))){
                this.quantDoProduto.set(i, this.quantDoProduto.get(i) + 1);
                return true;
            }
        }
        this.produtosNoEstoque.add(produto);
        this.quantDoProduto.add(1);
        return true;

    }

    public boolean removerDoEstoque(Produtos produto){
        for(int i = 0; i <= this.produtosNoEstoque.size(); i++){
            if(produto.equals(this.produtosNoEstoque.get(i))){
                if(this.quantDoProduto.get(i) == 0){
                    this.quantDoProduto.remove(i);
                    this.produtosNoEstoque.remove(i);
                } else{
                    this.quantDoProduto.set(i, this.quantDoProduto.get(i) - 1);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String aux = "ESTOQUE: \n";
        for(int i = 0; i <= this.produtosNoEstoque.size(); i++){
            aux += this.produtosNoEstoque.get(i).getNome() + "   quantidade: " + this.quantDoProduto.get(i) + "\n";
        }
        return aux;
    }





    Estoque(List<Produtos> produtosNoEstoque, List<Integer> quantDoProduto){
        this.produtosNoEstoque = produtosNoEstoque;
        this.quantDoProduto = quantDoProduto;
    }


}
