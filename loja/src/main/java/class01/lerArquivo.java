package class01;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class lerArquivo {
    public static List<Gerente> lArquivo(String path) {
        List<Gerente> gerentes = new ArrayList<>();
        try {
            List<List<Caixa>> caixas = new ArrayList<>();
            List<Pessoas> pessoas = new ArrayList<>();
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Gerente");

            for(int i = 0; i < nodeList.getLength(); i ++) {
                Element itemElement = (Element)nodeList.item(i);

                Gerente gerente1 = criarGerente(nodeList.item(i), pessoas);
                //System.out.println(gerente1);
                pessoas.add(gerente1);
                gerentes.add(gerente1);

                Element aux = (Element)itemElement.getElementsByTagName("caixasContratados").item(0);
                NodeList CaixasNodes = aux.getElementsByTagName("Caixa");
                List<Caixa> caixas2 = new ArrayList<>();
                for(int j=0; j < CaixasNodes.getLength(); j++){
                    //System.out.println(CaixasNodes.item(j).getNodeName());
                    Caixa caixa1 = criarCaixa(CaixasNodes.item(j), pessoas);
                    gerente1.addCaixaCont(caixa1);
                    pessoas.add(caixa1);
                    caixas2.add(caixa1);
                }
                caixas.add(caixas2);

                
            }
            for(int i = 0; i < nodeList.getLength(); i ++) {

                Element itemElement = (Element)nodeList.item(i);



                Element aux = (Element)itemElement.getElementsByTagName("caixasContratados").item(0);
                NodeList CaixasNodes = aux.getElementsByTagName("Caixa");
                for(int j=0; j < CaixasNodes.getLength(); j++){
                    Element caixaElement = (Element)CaixasNodes.item(j);
                    caixas.get(i).get(j).setClientes(lerListaClientes(caixaElement.getElementsByTagName("clientes").item(0), pessoas));
                }

                
            }
            
        } catch(Exception e){
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
    
        }

        return gerentes;
    }

    public static List<Gerente> ler() {
        String path = "projeto_final_322\\loja\\src\\main\\java\\class01\\save\\output.xml";
        return lArquivo(path);
    }



    @SuppressWarnings("exports")
    public static Produtos lerProduto(Element element){
        double precoCusto = Double.parseDouble(element.getElementsByTagName("precoCusto").item(0).getTextContent());
        double precoVenda = Double.parseDouble(element.getElementsByTagName("precoVenda").item(0).getTextContent());
        String nomePr = element.getElementsByTagName("nome").item(0).getTextContent();
        String marca = element.getElementsByTagName("marca").item(0).getTextContent();
        String fornecedor = element.getElementsByTagName("fornecedor").item(0).getTextContent();
        boolean promocao = stringToBoolean(element.getElementsByTagName("promocao").item(0).getTextContent());
        String pathPr = element.getElementsByTagName("path").item(0).getTextContent();

        //System.out.println(element.getElementsByTagName("teclado").item(0));

        if(element.getElementsByTagName("teclado").item(0) == null && element.getElementsByTagName("qtdChips").item(0) == null){//Tv

            double tamanhoTela = Double.parseDouble(element.getElementsByTagName("tamanhoTela").item(0).getTextContent());
            String resolucaoTela = element.getElementsByTagName("resolucaoTela").item(0).getTextContent();
            String tipoTela = element.getElementsByTagName("tipoTela").item(0).getTextContent();
            boolean smart = stringToBoolean(element.getElementsByTagName("smart").item(0).getTextContent());
            boolean suport = stringToBoolean(element.getElementsByTagName("suport").item(0).getTextContent());
            double consumoEnergia = Double.parseDouble(element.getElementsByTagName("consumoEnergia").item(0).getTextContent());
            return new Tv(precoCusto, precoVenda, nomePr, marca, fornecedor, promocao, pathPr, tamanhoTela,
                        resolucaoTela, tipoTela, smart, suport, consumoEnergia);

        } else if(element.getElementsByTagName("smart").item(0) == null && element.getElementsByTagName("caneta").item(0) == null){//Celular

            double tamanhoTela = Double.parseDouble(element.getElementsByTagName("tamanhoTela").item(0).getTextContent());
            int qtdChips = Integer.parseInt(element.getElementsByTagName("qtdChips").item(0).getTextContent());
            int armazenamento = Integer.parseInt(element.getElementsByTagName("armazenamento").item(0).getTextContent());
            int resolucaoCamera = Integer.parseInt(element.getElementsByTagName("resolucaoCamera").item(0).getTextContent());
            int capacidadeBateria = Integer.parseInt(element.getElementsByTagName("capacidadeBateria").item(0).getTextContent());
            String resistencias = element.getElementsByTagName("resistencias").item(0).getTextContent();
            return new Celular(precoCusto, precoVenda, nomePr, marca, fornecedor, promocao, pathPr, tamanhoTela,
                            qtdChips, armazenamento, resolucaoCamera, capacidadeBateria, resistencias);

        } else if(element.getElementsByTagName("smart").item(0) == null && element.getElementsByTagName("qtdChips").item(0) == null){//Tablet

            double tamanhoTela = Double.parseDouble(element.getElementsByTagName("tamanhoTela").item(0).getTextContent());
            int armazenamento = Integer.parseInt(element.getElementsByTagName("armazenamento").item(0).getTextContent());
            int resolucaoCamera = Integer.parseInt(element.getElementsByTagName("resolucaoCamera").item(0).getTextContent());
            int capacidadeBateria = Integer.parseInt(element.getElementsByTagName("capacidadeBateria").item(0).getTextContent());
            boolean caneta = stringToBoolean(element.getElementsByTagName("caneta").item(0).getTextContent());
            boolean teclado = stringToBoolean(element.getElementsByTagName("teclado").item(0).getTextContent());

            return new Tablet(precoCusto, precoVenda, nomePr, marca, fornecedor, promocao, pathPr, tamanhoTela,
                            armazenamento, resolucaoCamera, capacidadeBateria, caneta, teclado);

        } else return null;
    }



    @SuppressWarnings("exports")
    public static Compra lerCompra(Node node){
        Element element = (Element) node;

        List<Produtos> produtosCarrinho = new ArrayList<>();
        List<Integer> quantidadeCarrinho = new ArrayList<>();

        if(element.getChildNodes().getLength() == 0){
            return null;
        }
        NodeList aux = element.getElementsByTagName("Produtos");
        if((((Element)aux.item(0)).getElementsByTagName("Produto")).getLength() == 0){
            return null;
        }

        NodeList listaProdutosCarrinhoNodes = element.getElementsByTagName("Produtos");
        NodeList listaquantidadesCarrinhoNodes =element.getElementsByTagName("quantidade");

        for(int j = 0; j < ((Element)listaProdutosCarrinhoNodes.item(0)).getElementsByTagName("Produto").getLength(); j++){
            NodeList produtosCarrinhoNodes = ((Element)listaProdutosCarrinhoNodes.item(0)).getElementsByTagName("Produto");
            Element produtosCarrinhoElement = (Element)produtosCarrinhoNodes.item(j);
            if(lerProduto(produtosCarrinhoElement) != null){
                produtosCarrinho.add(lerProduto(produtosCarrinhoElement));
                quantidadeCarrinho.add(Integer.parseInt(((Element)listaquantidadesCarrinhoNodes.item(0)).getElementsByTagName("int").item(j).getTextContent()));
            }
        }

        double desconto = Double.parseDouble(element.getElementsByTagName("desconto").item(0).getTextContent());
        double valorPago = Double.parseDouble(element.getElementsByTagName("valorPago").item(0).getTextContent());
        LocalDate dataDoPedido = LocalDate.parse(element.getElementsByTagName("dataDoPedido").item(0).getTextContent());

        LocalDate dataDoPagamento = null;
        if(!element.getElementsByTagName("dataDoPagamento").item(0).getTextContent().equals("null")){
            dataDoPagamento = LocalDate.parse(element.getElementsByTagName("dataDoPagamento").item(0).getTextContent());
        }

        return new Compra(produtosCarrinho, quantidadeCarrinho, desconto, valorPago, dataDoPedido, dataDoPagamento);
    }




    @SuppressWarnings("exports")
    public static PedidoDeAumento lerPedidoDeAumento(Node node){

        if(node.getChildNodes().getLength() != 0){

            Element pedidoAumentoelement = (Element) node;

            String idRequerente = pedidoAumentoelement.getElementsByTagName("idRequerente").item(0).getTextContent();
            LocalDate data = LocalDate.parse(pedidoAumentoelement.getElementsByTagName("data").item(0).getTextContent());
    

            return new PedidoDeAumento(idRequerente, data);
        } else return null;
    }

    @SuppressWarnings("exports")
    public static Estoque lerEstoque(Node node){
        if(node.getChildNodes().getLength() == 0) return null;

        List<Produtos> produtosEstoque = new ArrayList<>();
        List<Integer> quantidadeEstoque = new ArrayList<>();

        Element estoqueElement = (Element) node;

        Element produtosNoEstoqueElement = (Element) estoqueElement.getElementsByTagName("produtosNoEstoque").item(0);
        Element quantDoProdutoElement = (Element) estoqueElement.getElementsByTagName("quantDoProduto").item(0);

        for(int j = 0; j < produtosNoEstoqueElement.getElementsByTagName("Produto").getLength(); j++){
            Element produtoElement = (Element) produtosNoEstoqueElement.getElementsByTagName("Produto").item(j);
            if(lerProduto(produtoElement) != null){
                produtosEstoque.add(lerProduto(produtoElement));
                quantidadeEstoque.add(Integer.parseInt(quantDoProdutoElement.getElementsByTagName("int").item(j).getTextContent()));
            }
        }
        return Estoque.getInstance(produtosEstoque, quantidadeEstoque);
    }

    @SuppressWarnings("exports")
    public static PedidoDeEstoque lerPedidoDeEstoque(Node node){
        if(node.getChildNodes().getLength() != 0){


            Element pedidoDeEstoqueElement = (Element) node;


            String idGerenteResponsavel = pedidoDeEstoqueElement.getElementsByTagName("idGerenteResponsavel").item(0).getTextContent();
            List<Produtos> produtosPEstoque = new ArrayList<>();
            List<Integer> quantidadePEstoque = new ArrayList<>();

            Element produtos = (Element)pedidoDeEstoqueElement.getElementsByTagName("produtos").item(0);
            Element quantidade = (Element)pedidoDeEstoqueElement.getElementsByTagName("quantidade").item(0);
            NodeList produto = produtos.getElementsByTagName("Produto");
            NodeList int_ = quantidade.getElementsByTagName("int");

            for(int j = 0; j < produto.getLength(); j++){

                Element produtosPEstoqueElement = (Element)produto.item(j);
                if(lerProduto(produtosPEstoqueElement) != null){
                    produtosPEstoque.add(lerProduto(produtosPEstoqueElement));
                    quantidadePEstoque.add(Integer.parseInt(int_.item(j).getTextContent()));
                }

            }


            LocalDate data = LocalDate.parse(pedidoDeEstoqueElement.getElementsByTagName("data").item(0).getTextContent());
            double valorTotal = Double.parseDouble(pedidoDeEstoqueElement.getElementsByTagName("valorTotal").item(0).getTextContent());


            return new PedidoDeEstoque(idGerenteResponsavel, produtosPEstoque, quantidadePEstoque, data, valorTotal);
        } else return null;
    }

    //////////////////////////////////////////////////////////////////////
    @SuppressWarnings("exports")
    public static List<Pessoas> lerListaClientes(Node node, List<Pessoas> pessoas){
        List<Pessoas> clientes = new ArrayList<>();
        Element element = (Element)node;
        for(int i = 0; i < element.getElementsByTagName("Cliente").getLength(); i++){
            Element clienteElement  = (Element)element.getElementsByTagName("Cliente").item(i);
            if(clienteElement.getElementsByTagName("nome").getLength() >0){


                String nome = clienteElement.getElementsByTagName("nome").item(0).getTextContent();
                String id = clienteElement.getElementsByTagName("id").item(0).getTextContent();
                double descontoEspecial = Double.parseDouble(clienteElement.getElementsByTagName("descontoEspecial").item(0).getTextContent());
                double saldo = Double.parseDouble(clienteElement.getElementsByTagName("saldo").item(0).getTextContent());

                Compra carrinho = lerCompra(clienteElement.getElementsByTagName("carrinho").item(0));

                NodeList comprasAPNodes = ((Element)(clienteElement.getElementsByTagName("comprasAguardandoPostagem").item(0))).getElementsByTagName("compra");
                List<Compra> comprasAguardandoPostagem = new ArrayList<>();
                for(int j=0; j < comprasAPNodes.getLength(); j++){
                    comprasAguardandoPostagem.add(lerCompra(comprasAPNodes.item(j)));
                }

                NodeList comprasETNodes = ((Element)(clienteElement.getElementsByTagName("comprasEmTransito").item(0))).getElementsByTagName("compra");
                List<Compra> comprasEmTransito = new ArrayList<>();
                for(int j=0; j < comprasETNodes.getLength(); j++){
                    comprasEmTransito.add(lerCompra(comprasETNodes.item(j)));
                }

                NodeList HComprasNodes = ((Element)(clienteElement.getElementsByTagName("historicoDeCompras").item(0))).getElementsByTagName("compra");
                List<Compra> historicoDeCompras = new ArrayList<>();
                for(int j=0; j < HComprasNodes.getLength(); j++){
                    historicoDeCompras.add(lerCompra(HComprasNodes.item(j)));
                }

                clientes.add(new Cliente(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito, historicoDeCompras));

            } else{
                //clienteElement.getElementsByTagName("id").item(0).getTextContent();
                for(int j = 0; j< pessoas.size(); j++){
                    if(pessoas.get(j).getId().equals(clienteElement.getTextContent())){
                        int a = 0;
                        for(int k=0; k< clientes.size(); k++){
                            if(clientes.get(k).getId().equals(pessoas.get(j).getId())){
                                a+=1;
                            }
                        }
                        if(a<=0){
                            clientes.add(pessoas.get(j));
                            a = 0;
                        }
                    }
                }
            }
        }
        return clientes;
    }
    ///////////////////////////////////////////////////////////////////////








    @SuppressWarnings("exports")
    public static Caixa criarCaixa(Node node, List<Pessoas> pessoas){

        //System.out.println(node.getNodeName());
        Element itemElement = (Element)node;

        String nome = itemElement.getElementsByTagName("nome").item(0).getTextContent();
        String id = itemElement.getElementsByTagName("id").item(0).getTextContent();
        double descontoEspecial = Double.parseDouble(itemElement.getElementsByTagName("descontoEspecial").item(0).getTextContent());
        double saldo = Double.parseDouble(itemElement.getElementsByTagName("saldo").item(0).getTextContent());

        Compra carrinho = lerCompra(itemElement.getElementsByTagName("carrinho").item(0));

        NodeList comprasAPNodes = ((Element)itemElement.getElementsByTagName("comprasAguardandoPostagem").item(0)).getElementsByTagName("compra");
        List<Compra> comprasAguardandoPostagem = new ArrayList<>();
        for(int j=0; j < comprasAPNodes.getLength(); j++){
            comprasAguardandoPostagem.add(lerCompra(comprasAPNodes.item(j)));
        }

        NodeList comprasETNodes = ((Element)itemElement.getElementsByTagName("comprasEmTransito").item(0)).getElementsByTagName("compra");
        List<Compra> comprasEmTransito = new ArrayList<>();
        for(int j=0; j < comprasETNodes.getLength(); j++){
            comprasEmTransito.add(lerCompra(comprasETNodes.item(j)));
        }

        NodeList HComprasNodes = ((Element)itemElement.getElementsByTagName("historicoDeCompras").item(0)).getElementsByTagName("compra");
        List<Compra> historicoDeCompras = new ArrayList<>();
        for(int j=0; j < HComprasNodes.getLength(); j++){
            historicoDeCompras.add(lerCompra(HComprasNodes.item(j)));
        }


        LocalDate dataDeContratacao = LocalDate.parse(itemElement.getElementsByTagName("dataDeContratacao").item(0).getTextContent());
        double salario = Double.parseDouble(itemElement.getElementsByTagName("salario").item(0).getTextContent());
        int faltasSemJust = Integer.parseInt(itemElement.getElementsByTagName("faltasSemJust").item(0).getTextContent());
        PedidoDeAumento pedidoDeAumento = lerPedidoDeAumento(itemElement.getElementsByTagName("pedidoDeAumento").item(0));


        NodeList HDeAumentosNodes = ((Element)itemElement.getElementsByTagName("historicoDeAumentos").item(0)).getElementsByTagName("pedidoDeAumento");
        List<PedidoDeAumento> historicoDeAumentos = new ArrayList<>();
        for(int j=0; j < HDeAumentosNodes.getLength(); j++){
            if(lerPedidoDeAumento(HDeAumentosNodes.item(j)) != null){
                historicoDeAumentos.add(lerPedidoDeAumento(HDeAumentosNodes.item(j)));
            }
        }

        Estoque estoque = lerEstoque(itemElement.getElementsByTagName("estoque").item(0));

        NodeList PDeEstoqueNodes = ((Element)itemElement.getElementsByTagName("pedidosDeEstoque").item(0)).getElementsByTagName("pedidoDeEstoque");
        List<PedidoDeEstoque> pedidosDeEstoque = new ArrayList<>();
        for(int j=0; j < PDeEstoqueNodes.getLength(); j++){
            if(lerPedidoDeEstoque(PDeEstoqueNodes.item(j)) != null){
                pedidosDeEstoque.add(lerPedidoDeEstoque(PDeEstoqueNodes.item(j)));
            }
        }

        NodeList HDePEstoqueNodes = ((Element)itemElement.getElementsByTagName("historicoDePedidos").item(0)).getElementsByTagName("pedidoDeEstoque");
        List<PedidoDeEstoque> historicoDePedidos = new ArrayList<>();
        for(int j=0; j < HDePEstoqueNodes.getLength(); j++){
            if(lerPedidoDeEstoque(HDePEstoqueNodes.item(j)) != null){
                historicoDePedidos.add(lerPedidoDeEstoque(HDePEstoqueNodes.item(j)));
            }
        }


        //List<Pessoas> clientes = lerListaClientes(itemElement.getElementsByTagName("clientes").item(0));

        Caixa caixa =  new Caixa(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito,
                                 historicoDeCompras, dataDeContratacao, salario, faltasSemJust, pedidoDeAumento, historicoDeAumentos,
                                estoque, pedidosDeEstoque, historicoDePedidos);
        pessoas.add(caixa);
        //System.out.println(caixa);
        return caixa;
    
    }


    @SuppressWarnings("exports")
    public static Gerente criarGerente(Node node, List<Pessoas> pessoas){

        Element itemElement = (Element)node;

        String nome = itemElement.getElementsByTagName("nome").item(0).getTextContent();
        String id = itemElement.getElementsByTagName("id").item(0).getTextContent();
        double descontoEspecial = Double.parseDouble(itemElement.getElementsByTagName("descontoEspecial").item(0).getTextContent());
        double saldo = Double.parseDouble(itemElement.getElementsByTagName("saldo").item(0).getTextContent());

        Compra carrinho = lerCompra(itemElement.getElementsByTagName("carrinho").item(0));

        NodeList comprasAPNodes = ((Element)itemElement.getElementsByTagName("comprasAguardandoPostagem").item(0)).getElementsByTagName("compra");
        List<Compra> comprasAguardandoPostagem = new ArrayList<>();
        for(int j=0; j < comprasAPNodes.getLength(); j++){
            comprasAguardandoPostagem.add(lerCompra(comprasAPNodes.item(j)));
        }

        NodeList comprasETNodes = ((Element)itemElement.getElementsByTagName("comprasEmTransito").item(0)).getElementsByTagName("compra");
        List<Compra> comprasEmTransito = new ArrayList<>();
        for(int j=0; j < comprasETNodes.getLength(); j++){
            comprasEmTransito.add(lerCompra(comprasETNodes.item(j)));
        }

        NodeList HComprasNodes = ((Element)itemElement.getElementsByTagName("historicoDeCompras").item(0)).getElementsByTagName("compra");
        List<Compra> historicoDeCompras = new ArrayList<>();
        for(int j=0; j < HComprasNodes.getLength(); j++){
            historicoDeCompras.add(lerCompra(HComprasNodes.item(j)));
        }




        double cofre = Double.parseDouble(itemElement.getElementsByTagName("cofre").item(0).getTextContent());
        double lucro = Double.parseDouble(itemElement.getElementsByTagName("lucro").item(0).getTextContent());

        NodeList HRendimentosNodes = ((Element)itemElement.getElementsByTagName("historicoDeRendimentos").item(0)).getElementsByTagName("double");
        List<Double> historicoDeRendimentos = new ArrayList<>();
        if(HRendimentosNodes.getLength() != 0){
            for(int j=0; j < HRendimentosNodes.getLength(); j++){
                historicoDeRendimentos.add(Double.parseDouble(HRendimentosNodes.item(j).getTextContent()));
            }
        }


        Gerente gerente = new Gerente(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito,
                                      historicoDeCompras, cofre, lucro, historicoDeRendimentos);
        
        pessoas.add(gerente);
        return gerente;
    
    }










    public static boolean stringToBoolean(String Boolean){
        if(Boolean.equals("true")) return true;
        else return false;
    }

}