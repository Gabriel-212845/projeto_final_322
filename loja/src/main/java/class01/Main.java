package class01;

import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        //Gerente gerente = new Gerente("Arnaldo", "123456", 550, 0.1);
        //gerente.contratarCaixa(new Caixa("Jorge", "55555", 200, 950));
        //gerente.contratarCaixa(new Caixa("Fábio", "57777", 200, 950));
        //List<Produtos> produtos = new ArrayList<>();
        //List<Integer> quantidades = new ArrayList<>();
        //Celular celular1 = new Celular(100, 1000, "Celular1", "Blue", "Industrias cd", false, "naoTem",
        //                               35, 2, 500, 13, 5000, "À prova d'água");
        //Tv tv1 = new Tv(200, 900, "Tv1", "Red", "Seu Zé", true, "napTem",
        //                90, "1080", "LCD", false, true, 100);
        //produtos.add(celular1);
        //quantidades.add(3);
        //produtos.add(tv1);
        //quantidades.add(5);
        //Caixa.setEstoque(new Estoque(produtos, quantidades));
        //List<Gerente> gerentes = new ArrayList<>();
        //gerentes.add(gerente);

        //escrArquivo.salvar(gerentes);



        List<Gerente> gerentes = lerArquivo.lArquivo("projeto_final_322\\loja\\src\\main\\java\\class01\\save\\output.xml");
        System.out.println(Caixa.getEstoque());
    }
}
