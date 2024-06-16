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
    public List<Gerente> lArquivo(String path) {
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
                pessoas.add(gerente1);
                gerentes.add(gerente1);

                NodeList CaixasNodes = itemElement.getElementsByTagName("caixasContratados").item(0).getChildNodes();
                List<Caixa> caixas2 = new ArrayList<>();
                for(int j=0; j < CaixasNodes.getLength(); j++){
                    Caixa caixa1 = criarCaixa(CaixasNodes.item(j), pessoas);
                    gerente1.addCaixaCont(caixa1);
                    pessoas.add(caixa1);
                    caixas2.add(caixa1);
                }
                caixas.add(caixas2);

                
            }
            for(int i = 0; i < nodeList.getLength(); i ++) {

                Element itemElement = (Element)nodeList.item(i);

                NodeList CaixasNodes = itemElement.getElementsByTagName("caixasContratados").item(0).getChildNodes();
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



    public Produtos lerProduto(Element element){
        double precoCusto = Double.parseDouble(element.getElementsByTagName("precoCusto").item(0).getTextContent());
        double precoVenda = Double.parseDouble(element.getElementsByTagName("precoVenda").item(0).getTextContent());
        String nomePr = element.getElementsByTagName("nome").item(0).getTextContent();
        String marca = element.getElementsByTagName("marca").item(0).getTextContent();
        String fornecedor = element.getElementsByTagName("fornecedor").item(0).getTextContent();
        boolean promocao = stringToBoolean(element.getElementsByTagName("promocao").item(0).getTextContent());
        String pathPr = element.getElementsByTagName("path").item(0).getTextContent();

        if(element.getElementsByTagName("teclado") == null && element.getElementsByTagName("qtdChips") == null){//Tv

            double tamanhoTela = Double.parseDouble(element.getElementsByTagName("tamanhoTela").item(0).getTextContent());
            String resolucaoTela = element.getElementsByTagName("resolucaoTela").item(0).getTextContent();
            String tipoTela = element.getElementsByTagName("tipoTela").item(0).getTextContent();
            boolean smart = stringToBoolean(element.getElementsByTagName("smart").item(0).getTextContent());
            boolean suport = stringToBoolean(element.getElementsByTagName("suport").item(0).getTextContent());
            double consumoEnergia = Double.parseDouble(element.getElementsByTagName("consumoEnergia").item(0).getTextContent());
            return new Tv(precoCusto, precoVenda, nomePr, marca, fornecedor, promocao, pathPr, tamanhoTela,
                        resolucaoTela, tipoTela, smart, suport, consumoEnergia);

        } else if(element.getElementsByTagName("smart") == null && element.getElementsByTagName("caneta") == null){//Celular

            double tamanhoTela = Double.parseDouble(element.getElementsByTagName("tamanhoTela").item(0).getTextContent());
            int qtdChips = Integer.parseInt(element.getElementsByTagName("qtdChips").item(0).getTextContent());
            int armazenamento = Integer.parseInt(element.getElementsByTagName("armazenamento").item(0).getTextContent());
            int resolucaoCamera = Integer.parseInt(element.getElementsByTagName("resolucaoCamera").item(0).getTextContent());
            int capacidadeBateria = Integer.parseInt(element.getElementsByTagName("capacidadeBateria").item(0).getTextContent());
            String resistencias = element.getElementsByTagName("resistencias").item(0).getTextContent();
            return new Celular(precoCusto, precoVenda, nomePr, marca, fornecedor, promocao, pathPr, tamanhoTela,
                            qtdChips, armazenamento, resolucaoCamera, capacidadeBateria, resistencias);

        } else if(element.getElementsByTagName("smart") == null && element.getElementsByTagName("qtdChips") == null){//Tablet

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



    public Compra lerCompra(Node node){
        NodeList carrinhoNodes = node.getChildNodes();
        List<Produtos> produtosCarrinho = new ArrayList<>();
        List<Integer> quantidadeCarrinho = new ArrayList<>();

        NodeList listaProdutosCarrinhoNodes = carrinhoNodes.item(0).getChildNodes();
        NodeList listaquantidadesCarrinhoNodes = carrinhoNodes.item(1).getChildNodes();

        for(int j = 0; j < listaProdutosCarrinhoNodes.getLength(); j++){
            NodeList produtosCarrinhoNodes = listaProdutosCarrinhoNodes.item(j).getChildNodes();
            Element produtosCarrinhoElement = (Element)produtosCarrinhoNodes;
            if(lerProduto(produtosCarrinhoElement) != null){
                produtosCarrinho.add(lerProduto(produtosCarrinhoElement));
                quantidadeCarrinho.add(Integer.parseInt(listaquantidadesCarrinhoNodes.item(j).getTextContent()));
            }
        }

        double desconto = Double.parseDouble(carrinhoNodes.item(2).getTextContent());
        double valorPago = Double.parseDouble(carrinhoNodes.item(3).getTextContent());
        LocalDate dataDoPedido = LocalDate.parse(carrinhoNodes.item(4).getTextContent());

        LocalDate dataDoPagamento = null;
        if(!carrinhoNodes.item(5).getTextContent().equals("null")){
            dataDoPagamento = LocalDate.parse(carrinhoNodes.item(5).getTextContent());
        }

        return new Compra(produtosCarrinho, quantidadeCarrinho, desconto, valorPago, dataDoPedido, dataDoPagamento);
    }




    public PedidoDeAumento lerPedidoDeAumento(Node node){

        if(!node.getTextContent().equals("null")){


            NodeList pedidoAumentoNodes = node.getChildNodes();

            String idRequerente = pedidoAumentoNodes.item(0).getTextContent();
            LocalDate data = LocalDate.parse(pedidoAumentoNodes.item(1).getTextContent());
    

            return new PedidoDeAumento(idRequerente, data);
        } else return null;
    }

    public Estoque lerEstoque(Node node){

        NodeList estoqueNodes = node.getChildNodes();
        List<Produtos> produtosEstoque = new ArrayList<>();
        List<Integer> quantidadeEstoque = new ArrayList<>();

        NodeList listaProdutosEstoqueNodes = estoqueNodes.item(0).getChildNodes();
        NodeList listaquantidadesEstoqueNodes = estoqueNodes.item(1).getChildNodes();

        for(int j = 0; j < listaProdutosEstoqueNodes.getLength(); j++){
            NodeList produtosEstoqueNodes = listaProdutosEstoqueNodes.item(j).getChildNodes();
            Element produtosEstoqueElement = (Element)produtosEstoqueNodes;
            if(lerProduto(produtosEstoqueElement) != null){
                produtosEstoque.add(lerProduto(produtosEstoqueElement));
                quantidadeEstoque.add(Integer.parseInt(listaquantidadesEstoqueNodes.item(j).getTextContent()));
            }
        }


        return new Estoque(produtosEstoque, quantidadeEstoque);
    }

    public PedidoDeEstoque lerPedidoDeEstoque(Node node){
        if(!node.getTextContent().equals("null")){
            NodeList pedidoDeEstoqueNodes = node.getChildNodes();

            String idGerenteResponsavel = pedidoDeEstoqueNodes.item(0).getTextContent();
            List<Produtos> produtosPEstoque = new ArrayList<>();
            List<Integer> quantidadePEstoque = new ArrayList<>();

            NodeList listaProdutosPEstoqueNodes = pedidoDeEstoqueNodes.item(1).getChildNodes();
            NodeList listaquantidadesPEstoqueNodes = pedidoDeEstoqueNodes.item(2).getChildNodes();

            for(int j = 0; j < listaProdutosPEstoqueNodes.getLength(); j++){
                NodeList produtosPEstoqueNodes = listaProdutosPEstoqueNodes.item(j).getChildNodes();
                Element produtosPEstoqueElement = (Element)produtosPEstoqueNodes;
                if(lerProduto(produtosPEstoqueElement) != null){
                    produtosPEstoque.add(lerProduto(produtosPEstoqueElement));
                    quantidadePEstoque.add(Integer.parseInt(listaquantidadesPEstoqueNodes.item(j).getTextContent()));
                }
            }

            LocalDate data = LocalDate.parse(pedidoDeEstoqueNodes.item(3).getTextContent());
            double valorTotal = Double.parseDouble(pedidoDeEstoqueNodes.item(4).getTextContent());

            return new PedidoDeEstoque(idGerenteResponsavel, produtosPEstoque, quantidadePEstoque, data, valorTotal);
        } else return null;
    }

    //////////////////////////////////////////////////////////////////////
    public List<Pessoas> lerListaClientes(Node node, List<Pessoas> pessoas){
        List<Pessoas> clientes = new ArrayList<>();
        for(int i = 0; i < node.getChildNodes().getLength(); i++){
            Element clienteElement  = (Element)node.getChildNodes().item(i);
            if(!clienteElement.getTagName().equals("Caixa") && !clienteElement.getTagName().equals("Gerente")){


                String nome = clienteElement.getElementsByTagName("nome").item(0).getTextContent();
                String id = clienteElement.getElementsByTagName("id").item(0).getTextContent();
                double descontoEspecial = Double.parseDouble(clienteElement.getElementsByTagName("descontoEspecial").item(0).getTextContent());
                double saldo = Double.parseDouble(clienteElement.getElementsByTagName("saldo").item(0).getTextContent());

                Compra carrinho = lerCompra(clienteElement.getElementsByTagName("carrinho").item(0));

                NodeList comprasAPNodes = clienteElement.getElementsByTagName("comprasAguardandoPostagem").item(0).getChildNodes();
                List<Compra> comprasAguardandoPostagem = new ArrayList<>();
                for(int j=0; j < comprasAPNodes.getLength(); j++){
                    comprasAguardandoPostagem.add(lerCompra(comprasAPNodes.item(j)));
                }

                NodeList comprasETNodes = clienteElement.getElementsByTagName("comprasEmTransito").item(0).getChildNodes();
                List<Compra> comprasEmTransito = new ArrayList<>();
                for(int j=0; j < comprasETNodes.getLength(); j++){
                    comprasEmTransito.add(lerCompra(comprasETNodes.item(j)));
                }

                NodeList HComprasNodes = clienteElement.getElementsByTagName("historicoDeCompras").item(0).getChildNodes();
                List<Compra> historicoDeCompras = new ArrayList<>();
                for(int j=0; j < HComprasNodes.getLength(); j++){
                    historicoDeCompras.add(lerCompra(HComprasNodes.item(j)));
                }

                clientes.add(new Cliente(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito, historicoDeCompras));

            } else{
                clienteElement.getElementsByTagName("id").item(0).getTextContent();
                for(int j = 0; j< pessoas.size(); j++){
                    if(pessoas.get(j).getId().equals(clienteElement.getElementsByTagName("id").item(0).getTextContent())){
                        clientes.add(pessoas.get(j));
                    }
                }
            }
        }
        return clientes;
    }
    ///////////////////////////////////////////////////////////////////////








    public Caixa criarCaixa(Node node, List<Pessoas> pessoas){

        Element itemElement = (Element)node;

        String nome = itemElement.getElementsByTagName("nome").item(0).getTextContent();
        String id = itemElement.getElementsByTagName("id").item(0).getTextContent();
        double descontoEspecial = Double.parseDouble(itemElement.getElementsByTagName("descontoEspecial").item(0).getTextContent());
        double saldo = Double.parseDouble(itemElement.getElementsByTagName("saldo").item(0).getTextContent());

        Compra carrinho = lerCompra(itemElement.getElementsByTagName("carrinho").item(0));

        NodeList comprasAPNodes = itemElement.getElementsByTagName("comprasAguardandoPostagem").item(0).getChildNodes();
        List<Compra> comprasAguardandoPostagem = new ArrayList<>();
        for(int j=0; j < comprasAPNodes.getLength(); j++){
            comprasAguardandoPostagem.add(lerCompra(comprasAPNodes.item(j)));
        }

        NodeList comprasETNodes = itemElement.getElementsByTagName("comprasEmTransito").item(0).getChildNodes();
        List<Compra> comprasEmTransito = new ArrayList<>();
        for(int j=0; j < comprasETNodes.getLength(); j++){
            comprasEmTransito.add(lerCompra(comprasETNodes.item(j)));
        }

        NodeList HComprasNodes = itemElement.getElementsByTagName("historicoDeCompras").item(0).getChildNodes();
        List<Compra> historicoDeCompras = new ArrayList<>();
        for(int j=0; j < HComprasNodes.getLength(); j++){
            historicoDeCompras.add(lerCompra(HComprasNodes.item(j)));
        }


        LocalDate dataDeContratacao = LocalDate.parse(itemElement.getElementsByTagName("dataDeContratacao").item(0).getTextContent());
        double salario = Double.parseDouble(itemElement.getElementsByTagName("salario").item(0).getTextContent());
        int faltasSemJust = Integer.parseInt(itemElement.getElementsByTagName("faltasSemJust").item(0).getTextContent());
        PedidoDeAumento pedidoDeAumento = lerPedidoDeAumento(itemElement.getElementsByTagName("pedidoDeAumento").item(0));


        NodeList HDeAumentosNodes = itemElement.getElementsByTagName("historicoDeAumentos").item(0).getChildNodes();
        List<PedidoDeAumento> historicoDeAumentos = new ArrayList<>();
        for(int j=0; j < HDeAumentosNodes.getLength(); j++){
            if(lerPedidoDeAumento(HDeAumentosNodes.item(j)) != null){
                historicoDeAumentos.add(lerPedidoDeAumento(HDeAumentosNodes.item(j)));
            }
        }

        Estoque estoque = lerEstoque(itemElement.getElementsByTagName("estoque").item(0));

        NodeList PDeEstoqueNodes = itemElement.getElementsByTagName("pedidosDeEstoque").item(0).getChildNodes();
        List<PedidoDeEstoque> pedidosDeEstoque = new ArrayList<>();
        for(int j=0; j < PDeEstoqueNodes.getLength(); j++){
            if(lerPedidoDeEstoque(PDeEstoqueNodes.item(j)) != null){
                pedidosDeEstoque.add(lerPedidoDeEstoque(PDeEstoqueNodes.item(j)));
            }
        }

        NodeList HDePEstoqueNodes = itemElement.getElementsByTagName("historicoDePedidos").item(0).getChildNodes();
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
        return caixa;
    
    }


    public Gerente criarGerente(Node node, List<Pessoas> pessoas){

        Element itemElement = (Element)node;

        String nome = itemElement.getElementsByTagName("nome").item(0).getTextContent();
        String id = itemElement.getElementsByTagName("id").item(0).getTextContent();
        double descontoEspecial = Double.parseDouble(itemElement.getElementsByTagName("descontoEspecial").item(0).getTextContent());
        double saldo = Double.parseDouble(itemElement.getElementsByTagName("saldo").item(0).getTextContent());

        Compra carrinho = lerCompra(itemElement.getElementsByTagName("carrinho").item(0));

        NodeList comprasAPNodes = itemElement.getElementsByTagName("comprasAguardandoPostagem").item(0).getChildNodes();
        List<Compra> comprasAguardandoPostagem = new ArrayList<>();
        for(int j=0; j < comprasAPNodes.getLength(); j++){
            comprasAguardandoPostagem.add(lerCompra(comprasAPNodes.item(j)));
        }

        NodeList comprasETNodes = itemElement.getElementsByTagName("comprasEmTransito").item(0).getChildNodes();
        List<Compra> comprasEmTransito = new ArrayList<>();
        for(int j=0; j < comprasETNodes.getLength(); j++){
            comprasEmTransito.add(lerCompra(comprasETNodes.item(j)));
        }

        NodeList HComprasNodes = itemElement.getElementsByTagName("historicoDeCompras").item(0).getChildNodes();
        List<Compra> historicoDeCompras = new ArrayList<>();
        for(int j=0; j < HComprasNodes.getLength(); j++){
            historicoDeCompras.add(lerCompra(HComprasNodes.item(j)));
        }




        double cofre = Double.parseDouble(itemElement.getElementsByTagName("cofre").item(0).getTextContent());
        double lucro = Double.parseDouble(itemElement.getElementsByTagName("lucro").item(0).getTextContent());

        NodeList HRendimentosNodes = itemElement.getElementsByTagName("historicoDeRendimentos").item(0).getChildNodes();
        List<Double> historicoDeRendimentos = new ArrayList<>();
        for(int j=0; j < HRendimentosNodes.getLength(); j++){
            historicoDeRendimentos.add(Double.parseDouble(HRendimentosNodes.item(j).getTextContent()));
        }


        Gerente gerente = new Gerente(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito,
                                      historicoDeCompras, cofre, lucro, historicoDeRendimentos);
        
        pessoas.add(gerente);
        return gerente;
    
    }










    public boolean stringToBoolean(String Boolean){
        if(Boolean.equals("true")) return true;
        else return false;
    }

}