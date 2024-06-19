package class01;

import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {

        /*
        Gerente gerente = new Gerente("Arnaldo", "123456", 550, 0.1);
        gerente.contratarCaixa(new Caixa("Jorge", "55555", 200, 950));
        gerente.contratarCaixa(new Caixa("Fábio", "57777", 200, 950));
        List<Produtos> produtos = new ArrayList<>();
        List<Integer> quantidades = new ArrayList<>();
        Celular celular1 = new Celular(100, 1000, "Celular1", "Blue", "Industrias cd", false, "naoTem",
                                       35, 2, 500, 13, 5000, "À prova d'água");
        Tv tv1 = new Tv(200, 900, "Tv1", "Red", "Seu Zé", true, "napTem",
                        90, "1080", "LCD", false, true, 100);
        produtos.add(celular1);
        quantidades.add(3);
        produtos.add(tv1);
        quantidades.add(5);
        Gerente.setCofre(950000);
        List<Double> historicoDeRendimentos = new ArrayList<>();
        historicoDeRendimentos.add(90000.00);
        historicoDeRendimentos.add(85000.00);
        historicoDeRendimentos.add(97000.00);
        Caixa.setEstoque(new Estoque(produtos, quantidades));
        List<Gerente> gerentes = new ArrayList<>();
        gerentes.add(gerente);
        gerente.getCaixasContratados().get(0).setPedidoDeAumento(new PedidoDeAumento(gerente.getCaixasContratados().get(0).getId(), LocalDate.parse("2024-06-10")));
        PedidoDeAumento pedidosAntigos1 = new PedidoDeAumento(gerente.getCaixasContratados().get(0).getId(), LocalDate.parse("2024-05-10"));
        PedidoDeAumento pedidosAntigos2 = new PedidoDeAumento(gerente.getCaixasContratados().get(0).getId(), LocalDate.parse("2024-04-10"));
        List<PedidoDeAumento> historicoDeAumentos = new ArrayList<>();
        historicoDeAumentos.add(pedidosAntigos1);
        historicoDeAumentos.add(pedidosAntigos2);
        gerente.getCaixasContratados().get(0).setHistoricoDeAumentos(historicoDeAumentos);
        gerente.getCaixasContratados().get(0).setDataDeContratacao(LocalDate.parse("2024-05-10"));

        Celular celular2 = new Celular(100, 1000, "Celular2", "Blue", "Industrias cd", false, "naoTem",
                                       35, 2, 500, 13, 5000, "À prova d'água");
        Tv tv2 = new Tv(200, 900, "Tv2", "Red", "Seu Zé", true, "napTem",
                        90, "1080", "LCD", false, true, 100);
        List<Produtos> produtos1 = new ArrayList<>();
        List<Integer> quantidades1 = new ArrayList<>();
        produtos1.add(celular2);
        produtos1.add(tv2);
        quantidades1.add(5);
        quantidades1.add(2);

        Tv tv3 = new Tv(200, 900, "Tv3", "Red", "Seu Zé", true, "napTem",
                        90, "1080", "LCD", false, true, 100);
        List<Produtos> produtos2 = new ArrayList<>();
        List<Integer> quantidades2 = new ArrayList<>();
        produtos2.add(tv3);
        quantidades2.add(5);


        PedidoDeEstoque pedidoEstoque1 = new PedidoDeEstoque(gerente, produtos1, quantidades1);
        PedidoDeEstoque pedidoEstoque2 = new PedidoDeEstoque(gerente, produtos2, quantidades2);
        pedidoEstoque1.setData(LocalDate.parse("2024-05-08"));
        pedidoEstoque2.setData(LocalDate.parse("2024-05-06"));

        List<PedidoDeEstoque> pedidosDeEstoque = new ArrayList<>();
        pedidosDeEstoque.add(pedidoEstoque1);
        pedidosDeEstoque.add(pedidoEstoque2);

        gerente.getCaixasContratados().get(0).setPedidosDeEstoque(pedidosDeEstoque);

        Tv tv4 = new Tv(200, 900, "Tv4", "Red", "Seu Zé", true, "napTem",
                        90, "1080", "LCD", false, true, 100);
        List<Produtos> produtos3 = new ArrayList<>();
        List<Integer> quantidades3 = new ArrayList<>();
        produtos3.add(tv4);
        quantidades3.add(4);

        PedidoDeEstoque pedidoEstoque3 = new PedidoDeEstoque(gerente, produtos3, quantidades3);
        pedidoEstoque3.setData(LocalDate.parse("2024-04-08"));

        List<PedidoDeEstoque> pedidosDeEstoque1 = new ArrayList<>();
        pedidosDeEstoque1.add(pedidoEstoque3);

        gerente.getCaixasContratados().get(0).setHistoricoDePedidos(pedidosDeEstoque1);



        Tablet tablet1 = new Tablet(400, 700, "Tablet1", "marcaJ", "Zé", false, "qualda",
        20, 125, 8, 7000, true, false);
        List<Produtos> produtos4 = new ArrayList<>();
        List<Integer> quantidades4 = new ArrayList<>();
        produtos4.add(tablet1);
        quantidades4.add(1);

        Compra compra1 = new Compra(produtos4, quantidades4, 0.1);

        Tablet tablet2 = new Tablet(400, 700, "Tablet2", "marcaJ", "Zé", false, "qualda",
        20, 125, 8, 7000, true, false);
        List<Produtos> produtos5 = new ArrayList<>();
        List<Integer> quantidades5 = new ArrayList<>();
        produtos5.add(tablet2);
        quantidades5.add(1);

        Compra compra2 = new Compra(produtos5, quantidades5, 0.1);


        Tablet tablet3 = new Tablet(400, 700, "Tablet3", "marcaJ", "Zé", false, "qualda",
        20, 125, 8, 7000, true, false);
        List<Produtos> produtos6 = new ArrayList<>();
        List<Integer> quantidades6 = new ArrayList<>();
        produtos6.add(tablet3);
        quantidades6.add(2);

        Compra compra3 = new Compra(produtos6, quantidades6, 0.15);

        List<Compra> comprasAguardandoPostagem = new ArrayList<>();
        comprasAguardandoPostagem.add(compra2);
        comprasAguardandoPostagem.add(compra3);






        Cliente cliente1 = new Cliente("Roberto", "5445", 0.4, 500, compra1, comprasAguardandoPostagem, comprasAguardandoPostagem, comprasAguardandoPostagem);
        Cliente cliente2 = new Cliente("João", "88888", 0.2, 9000);

        List<Pessoas> pessoas = new ArrayList<>();
        pessoas.add(cliente1);
        pessoas.add(cliente2);
        pessoas.add(gerente.getCaixasContratados().get(1));
        pessoas.add(gerente);


        List<Pessoas> pessoas1 = new ArrayList<>();
        pessoas.add(gerente.getCaixasContratados().get(0));

        gerente.getCaixasContratados().get(0).setClientes(pessoas);
        gerente.getCaixasContratados().get(1).setClientes(pessoas1);

        escrArquivo.salvar(gerentes);
        */


        List<Gerente> gerentes = lerArquivo.lArquivo("projeto_final_322\\loja\\src\\main\\java\\class01\\save\\output.xml");
        System.out.println(Caixa.getEstoque());
    }
}
