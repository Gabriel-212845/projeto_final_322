package class01;

import java.util.List;

// Java Program to Write XML Using DOM Parser
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
  
public class escrArquivo {
    public static void salvar(List<Gerente> gerentes) throws Exception {
        // Create a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
  
        // Create a new Document
        Document doc = builder.newDocument();

        // Create root element
        Element root = doc.createElement("Gerentes");
        doc.appendChild(root);


        for(int i = 0; i< gerentes.size(); i++){
            Element gerente = doc.createElement("Gerente");
            root.appendChild(gerente);

            Element nome = doc.createElement("nome");
            nome.setTextContent(gerentes.get(i).getNome());
            gerente.appendChild(nome);


            Element id = doc.createElement("id");
            id.setTextContent(gerentes.get(i).getId());
            gerente.appendChild(id);

            Element descontoEspecial = doc.createElement("descontoEspecial");
            descontoEspecial.setTextContent(String.valueOf(gerentes.get(i).getDescontoEspecial()));
            gerente.appendChild(descontoEspecial);

            Element saldo = doc.createElement("saldo");
            saldo.setTextContent(String.valueOf(gerentes.get(i).getSaldo()));
            gerente.appendChild(saldo);

            escrCompra(doc, gerente, gerentes.get(i).getCarrinho(), "carrinho");

            Element comprasAguardandoPostagem = doc.createElement("comprasAguardandoPostagem");
            gerente.appendChild(comprasAguardandoPostagem);
            //if(gerentes.get(i).getComprasAguardandoPostagem().size() == 0) comprasAguardandoPostagem.setTextContent("null");
            for(int j=0; j < gerentes.get(i).getComprasAguardandoPostagem().size(); j++){
                escrCompra(doc, comprasAguardandoPostagem, gerentes.get(i).getComprasAguardandoPostagem().get(j), "compra");
            }

            Element comprasEmTransito = doc.createElement("comprasEmTransito");
            gerente.appendChild(comprasEmTransito);
            //if(gerentes.get(i).getComprasEmTransito().size() == 0) comprasEmTransito.setTextContent("null");
            for(int j=0; j < gerentes.get(i).getComprasEmTransito().size(); j++){
                escrCompra(doc, comprasEmTransito, gerentes.get(i).getComprasEmTransito().get(j), "compra");
            }

            Element historicoDeCompras = doc.createElement("historicoDeCompras");
            gerente.appendChild(historicoDeCompras);
            //if(gerentes.get(i).getHistoricoDeCompras().size() == 0) historicoDeCompras.setTextContent("null");
            for(int j=0; j < gerentes.get(i).getHistoricoDeCompras().size(); j++){
                escrCompra(doc, historicoDeCompras, gerentes.get(i).getHistoricoDeCompras().get(j), "compra");
            }


            Element cofre = doc.createElement("cofre");
            cofre.setTextContent(String.valueOf(Gerente.getCofre()));
            gerente.appendChild(cofre);

            Element lucro = doc.createElement("lucro");
            lucro.setTextContent(String.valueOf(gerentes.get(i).getLucro()));
            gerente.appendChild(lucro);

            Element caixasContratados = doc.createElement("caixasContratados");
            gerente.appendChild(caixasContratados);
            //if(gerentes.get(i).getCaixasContratados().size() == 0) caixasContratados.setTextContent("null");
            for(int j=0; j < gerentes.get(i).getCaixasContratados().size(); j++){
                escrCaixa(doc, caixasContratados, gerentes.get(i).getCaixasContratados().get(j));
            }

            Element historicoDeRendimentos = doc.createElement("historicoDeRendimentos");
            gerente.appendChild(historicoDeRendimentos);
            //if(gerentes.get(i).getHistoricoDeRendimentos().size() == 0) historicoDeRendimentos.setTextContent("null");
            for(int j=0; j<gerentes.get(i).getHistoricoDeRendimentos().size(); j++){
                Element quantidade = doc.createElement("double");
                quantidade.setTextContent(String.valueOf(gerentes.get(i).getHistoricoDeRendimentos().get(j)));
                historicoDeRendimentos.appendChild(quantidade);
            }




        }
        
  
  

  
  
        // Specify your local file path
        StreamResult result = new StreamResult("projeto_final_322\\loja\\src\\main\\java\\class01\\save\\output.xml");
        writeXml(doc, result);
        System.out.println("XML file created successfully!");
    }

    public static void escrCaixa(Document doc, Element element, Caixa caixa){
        Element caixaElement = doc.createElement("Caixa");
        element.appendChild(caixaElement);

        Element nome = doc.createElement("nome");
        nome.setTextContent(caixa.getNome());
        caixaElement.appendChild(nome);


        Element id = doc.createElement("id");
        id.setTextContent(caixa.getId());
        caixaElement.appendChild(id);

        Element descontoEspecial = doc.createElement("descontoEspecial");
        descontoEspecial.setTextContent(String.valueOf(caixa.getDescontoEspecial()));
        caixaElement.appendChild(descontoEspecial);

        Element saldo = doc.createElement("saldo");
        saldo.setTextContent(String.valueOf(caixa.getSaldo()));
        caixaElement.appendChild(saldo);

        escrCompra(doc, caixaElement, caixa.getCarrinho(), "carrinho");

        Element comprasAguardandoPostagem = doc.createElement("comprasAguardandoPostagem");
        caixaElement.appendChild(comprasAguardandoPostagem);
        //if(caixa.getComprasAguardandoPostagem().size() == 0) comprasAguardandoPostagem.setTextContent("null");
        for(int j=0; j < caixa.getComprasAguardandoPostagem().size(); j++){
            escrCompra(doc, comprasAguardandoPostagem, caixa.getComprasAguardandoPostagem().get(j), "compra");
        }

        Element comprasEmTransito = doc.createElement("comprasEmTransito");
        caixaElement.appendChild(comprasEmTransito);
        //if(caixa.getComprasEmTransito().size() == 0) comprasEmTransito.setTextContent("null");
        for(int j=0; j < caixa.getComprasEmTransito().size(); j++){
            escrCompra(doc, comprasEmTransito, caixa.getComprasEmTransito().get(j), "compra");
        }

        Element historicoDeCompras = doc.createElement("historicoDeCompras");
        caixaElement.appendChild(historicoDeCompras);
        //if(caixa.getHistoricoDeCompras().size() == 0) historicoDeCompras.setTextContent("null");
        for(int j=0; j < caixa.getHistoricoDeCompras().size(); j++){
            escrCompra(doc, historicoDeCompras, caixa.getHistoricoDeCompras().get(j), "compra");
        }


        Element dataDeContratacao = doc.createElement("dataDeContratacao");
        dataDeContratacao.setTextContent(String.valueOf(caixa.getDataDeContratacao()));
        caixaElement.appendChild(dataDeContratacao);

        Element salario = doc.createElement("salario");
        salario.setTextContent(String.valueOf(caixa.getSalario()));
        caixaElement.appendChild(salario);

        Element faltasSemJust = doc.createElement("faltasSemJust");
        faltasSemJust.setTextContent(String.valueOf(caixa.getFaltasSemJust()));
        caixaElement.appendChild(faltasSemJust);

        escrPedidoDeAumento(doc, caixaElement, caixa.getPedidoDeAumento());

        Element historicoDeAumentos = doc.createElement("historicoDeAumentos");
        caixaElement.appendChild(historicoDeAumentos);
        //if(caixa.getHistoricoDeAumentos().size() == 0) historicoDeAumentos.setTextContent("null");
        for(int j=0; j < caixa.getHistoricoDeAumentos().size(); j++){
            escrPedidoDeAumento(doc, historicoDeAumentos, caixa.getHistoricoDeAumentos().get(j));
        }

        escrEstoque(doc, caixaElement, Caixa.getEstoque());

        Element pedidosDeEstoque = doc.createElement("pedidosDeEstoque");
        caixaElement.appendChild(pedidosDeEstoque);
        //if(caixa.getPedidosDeEstoque().size() == 0) pedidosDeEstoque.setTextContent("null");
        for(int j=0; j < caixa.getPedidosDeEstoque().size(); j++){
            escrPedidoDeEstoque(doc, pedidosDeEstoque, caixa.getPedidosDeEstoque().get(j));
        }

        Element historicoDePedidos = doc.createElement("historicoDePedidos");
        caixaElement.appendChild(historicoDePedidos);
        //if(caixa.getHistoricoDePedidos().size() == 0) historicoDePedidos.setTextContent("null");
        for(int j=0; j < caixa.getHistoricoDePedidos().size(); j++){
            escrPedidoDeEstoque(doc, historicoDePedidos, caixa.getHistoricoDePedidos().get(j));
        }

        escrClientes(doc, caixaElement, caixa.getClientes());

    }

    public static void escrClientes(Document doc, Element element, List<Pessoas> clientes){
        Element clientesElement = doc.createElement("clientes");
        element.appendChild(clientesElement);

        //if(clientes.size() == 0) clientesElement.setTextContent("null");
        for(int j=0; j < clientes.size(); j++){
            if(clientes.get(j).getClasse().equals("Cliente")){
                escrCliente(doc, clientesElement, (Cliente)clientes.get(j));
            } else{
                Element clienteElement = doc.createElement("Cliente");
                clienteElement.setTextContent(clientes.get(j).getId());
                clientesElement.appendChild(clienteElement);
            }
        }

        
    }

    public static void escrCliente(Document doc, Element element, Cliente cliente){
        Element clienteElement = doc.createElement("Cliente");
        element.appendChild(clienteElement);

        Element nome = doc.createElement("nome");
        nome.setTextContent(cliente.getNome());
        clienteElement.appendChild(nome);


        Element id = doc.createElement("id");
        id.setTextContent(cliente.getId());
        clienteElement.appendChild(id);

        Element descontoEspecial = doc.createElement("descontoEspecial");
        descontoEspecial.setTextContent(String.valueOf(cliente.getDescontoEspecial()));
        clienteElement.appendChild(descontoEspecial);

        Element saldo = doc.createElement("saldo");
        saldo.setTextContent(String.valueOf(cliente.getSaldo()));
        clienteElement.appendChild(saldo);

        escrCompra(doc, clienteElement, cliente.getCarrinho(), "carrinho");

        Element comprasAguardandoPostagem = doc.createElement("comprasAguardandoPostagem");
        clienteElement.appendChild(comprasAguardandoPostagem);
        //if(cliente.getComprasAguardandoPostagem().size() == 0) comprasAguardandoPostagem.setTextContent("null");
        for(int j=0; j < cliente.getComprasAguardandoPostagem().size(); j++){
            escrCompra(doc, comprasAguardandoPostagem, cliente.getComprasAguardandoPostagem().get(j), "compra");
        }

        Element comprasEmTransito = doc.createElement("comprasEmTransito");
        clienteElement.appendChild(comprasEmTransito);
        //if(cliente.getComprasEmTransito().size() == 0) comprasEmTransito.setTextContent("null");
        for(int j=0; j < cliente.getComprasEmTransito().size(); j++){
            escrCompra(doc, comprasEmTransito, cliente.getComprasEmTransito().get(j), "compra");
        }

        Element historicoDeCompras = doc.createElement("historicoDeCompras");
        clienteElement.appendChild(historicoDeCompras);
        //if(cliente.getHistoricoDeCompras().size() == 0) historicoDeCompras.setTextContent("null");
        for(int j=0; j < cliente.getHistoricoDeCompras().size(); j++){
            escrCompra(doc, historicoDeCompras, cliente.getHistoricoDeCompras().get(j), "compra");
        }
        
    }


    public static void escrPedidoDeEstoque(Document doc, Element element, PedidoDeEstoque pedidoDeEstoque){
        Element pedidoDeEstoqueElement = doc.createElement("pedidoDeEstoque");
        element.appendChild(pedidoDeEstoqueElement);

        Element idGerenteResponsavel = doc.createElement("idGerenteResponsavel");
        idGerenteResponsavel.setTextContent(pedidoDeEstoque.getIdGerenteResponsavel());
        pedidoDeEstoqueElement.appendChild(idGerenteResponsavel);

        Element produtos = doc.createElement("produtos");
        pedidoDeEstoqueElement.appendChild(produtos);
        for(int j=0; j < pedidoDeEstoque.getProdutos().size(); j++){
            escrProduto(doc, produtos, pedidoDeEstoque.getProdutos().get(j));
        }

        Element quantidade = doc.createElement("quantidade");
        pedidoDeEstoqueElement.appendChild(quantidade);
        for(int j=0; j < pedidoDeEstoque.getQuantidade().size(); j++){
            Element quantidadeItem = doc.createElement("int");
            quantidadeItem.setTextContent(String.valueOf(pedidoDeEstoque.getQuantidade().get(j)));
            quantidade.appendChild(quantidadeItem);
        }

        Element data = doc.createElement("data");
        data.setTextContent(String.valueOf(pedidoDeEstoque.getData()));
        pedidoDeEstoqueElement.appendChild(data);

        Element valorTotal = doc.createElement("valorTotal");
        valorTotal.setTextContent(String.valueOf(pedidoDeEstoque.getValorTotal()));
        pedidoDeEstoqueElement.appendChild(valorTotal);



    }


    public static void escrEstoque(Document doc, Element element, Estoque estoque){
        Element estoqueElement = doc.createElement("estoque");
        element.appendChild(estoqueElement);
        if(estoque != null){

            Element produtosNoEstoque = doc.createElement("produtosNoEstoque");
            estoqueElement.appendChild(produtosNoEstoque);
            for(int j=0; j < estoque.getProdutosNoEstoque().size(); j++){
                escrProduto(doc, produtosNoEstoque, estoque.getProdutosNoEstoque().get(j));
            }

            Element quantDoProduto = doc.createElement("quantDoProduto");
            estoqueElement.appendChild(quantDoProduto);
            for(int j=0; j < estoque.getQuantDoProduto().size(); j++){
                Element quantidade = doc.createElement("int");
                quantidade.setTextContent(String.valueOf(estoque.getQuantDoProduto().get(j)));
                quantDoProduto.appendChild(quantidade);
            }

        } //else estoqueElement.setTextContent("null");
    }



    public static void escrPedidoDeAumento(Document doc, Element element, PedidoDeAumento pedidoDeAumento){
        Element pedidoDeAumentoElement = doc.createElement("pedidoDeAumento");
        element.appendChild(pedidoDeAumentoElement);

        if(pedidoDeAumento != null){
            Element idRequerente = doc.createElement("idRequerente");
            idRequerente.setTextContent(pedidoDeAumento.getIdRequerente());
            pedidoDeAumentoElement.appendChild(idRequerente);

            Element data = doc.createElement("data");
            data.setTextContent(String.valueOf(pedidoDeAumento.getData()));
            pedidoDeAumentoElement.appendChild(data);
        } //else pedidoDeAumentoElement.setTextContent("null");


    }









    public static void escrCompra(Document doc, Element element, Compra compra, String nomeCompra){
        Element compraElement = doc.createElement(nomeCompra);
        element.appendChild(compraElement);
        if(compra != null){

            Element produtosElement = doc.createElement("Produtos");
            compraElement.appendChild(produtosElement);

            for(int i=0; i<compra.getProdutos().size(); i++){
                escrProduto(doc, produtosElement, compra.getProdutos().get(i));
            }

            Element quantidadeElement = doc.createElement("quantidade");
            compraElement.appendChild(quantidadeElement);
            for(int i=0; i<compra.getQuantidade().size(); i++){
                Element quantidade = doc.createElement("int");
                quantidade.setTextContent(String.valueOf(compra.getQuantidade().get(i)));
                quantidadeElement.appendChild(quantidade);
            }

            Element desconto = doc.createElement("desconto");
            desconto.setTextContent(String.valueOf(compra.getDesconto()));
            compraElement.appendChild(desconto);

            Element valorPago = doc.createElement("valorPago");
            valorPago.setTextContent(String.valueOf(compra.getValorPago()));
            compraElement.appendChild(valorPago);

            Element dataDoPedido = doc.createElement("dataDoPedido");
            dataDoPedido.setTextContent(String.valueOf(compra.getDataDoPedido()));
            compraElement.appendChild(dataDoPedido);

            Element dataDoPagamento = doc.createElement("dataDoPagamento");
            dataDoPagamento.setTextContent(String.valueOf(compra.getDataDoPagamento()));
            compraElement.appendChild(dataDoPagamento);

        } //else compraElement.setTextContent("null");



    }



    public static void escrProduto(Document doc, Element element, Produtos produto){
        Element produtoElement = doc.createElement("Produto");
        element.appendChild(produtoElement);


        Element precoCusto = doc.createElement("precoCusto");
        precoCusto.setTextContent(String.valueOf(produto.getPrecoCusto()));
        produtoElement.appendChild(precoCusto);

        Element precoVenda = doc.createElement("precoVenda");
        precoVenda.setTextContent(String.valueOf(produto.getPrecoVenda()));
        produtoElement.appendChild(precoVenda);

        Element nome = doc.createElement("nome");
        nome.setTextContent(produto.getNome());
        produtoElement.appendChild(nome);

        Element marca = doc.createElement("marca");
        marca.setTextContent(produto.getMarca());
        produtoElement.appendChild(marca);

        Element fornecedor = doc.createElement("fornecedor");
        fornecedor.setTextContent(produto.getFornecedor());
        produtoElement.appendChild(fornecedor);

        Element promocao = doc.createElement("promocao");
        promocao.setTextContent(String.valueOf(produto.isPromocao()));
        produtoElement.appendChild(promocao);

        Element path = doc.createElement("path");
        path.setTextContent(produto.getPath());
        produtoElement.appendChild(path);

        if(produto.getClasse().equals("Tv")){

            Element tamanhoTela = doc.createElement("tamanhoTela");
            tamanhoTela.setTextContent(String.valueOf( ((Tv)produto).getTamanhoTela()));
            produtoElement.appendChild(tamanhoTela);

            Element resolucaoTela = doc.createElement("resolucaoTela");
            resolucaoTela.setTextContent(((Tv)produto).getResolucaoTela());
            produtoElement.appendChild(resolucaoTela);

            Element tipoTela = doc.createElement("tipoTela");
            tipoTela.setTextContent(((Tv)produto).getTipoTela());
            produtoElement.appendChild(tipoTela);

            Element smart = doc.createElement("smart");
            smart.setTextContent(String.valueOf(((Tv)produto).isSmart()));
            produtoElement.appendChild(smart);

            Element suport = doc.createElement("suport");
            suport.setTextContent(String.valueOf(((Tv)produto).isSuport()));
            produtoElement.appendChild(suport);

            Element consumoEnergia = doc.createElement("consumoEnergia");
            consumoEnergia.setTextContent(String.valueOf( ((Tv)produto).getConsumoEnergia()));
            produtoElement.appendChild(consumoEnergia);

        } else if(produto.getClasse().equals("Celular")){

            Element tamanhoTela = doc.createElement("tamanhoTela");
            tamanhoTela.setTextContent(String.valueOf( ((Celular)produto).getTamanhoTela()));
            produtoElement.appendChild(tamanhoTela);

            Element qtdChips = doc.createElement("qtdChips");
            qtdChips.setTextContent(String.valueOf( ((Celular)produto).getQtdChips()));
            produtoElement.appendChild(qtdChips);

            Element armazenamento = doc.createElement("armazenamento");
            armazenamento.setTextContent(String.valueOf( ((Celular)produto).getArmazenamento()));
            produtoElement.appendChild(armazenamento);

            Element resolucaoCamera = doc.createElement("resolucaoCamera");
            resolucaoCamera.setTextContent(String.valueOf( ((Celular)produto).getResolucaoCamera()));
            produtoElement.appendChild(resolucaoCamera);

            Element capacidadeBateria = doc.createElement("capacidadeBateria");
            capacidadeBateria.setTextContent(String.valueOf( ((Celular)produto).getCapacidadeBateria()));
            produtoElement.appendChild(capacidadeBateria);

            Element resistencias = doc.createElement("resistencias");
            resistencias.setTextContent(((Celular)produto).getResistencias());
            produtoElement.appendChild(resistencias);

            
        } else if(produto.getClasse().equals("Tablet")){

            Element tamanhoTela = doc.createElement("tamanhoTela");
            tamanhoTela.setTextContent(String.valueOf( ((Tablet)produto).getTamanhoTela()));
            produtoElement.appendChild(tamanhoTela);

            Element armazenamento = doc.createElement("armazenamento");
            armazenamento.setTextContent(String.valueOf( ((Tablet)produto).getArmazenamento()));
            produtoElement.appendChild(armazenamento);

            Element resolucaoCamera = doc.createElement("resolucaoCamera");
            resolucaoCamera.setTextContent(String.valueOf( ((Tablet)produto).getResolucaoCamera()));
            produtoElement.appendChild(resolucaoCamera);

            Element capacidadeBateria = doc.createElement("capacidadeBateria");
            capacidadeBateria.setTextContent(String.valueOf( ((Tablet)produto).getCapacidadeBateria()));
            produtoElement.appendChild(capacidadeBateria);

            Element caneta = doc.createElement("caneta");
            caneta.setTextContent(String.valueOf( ((Tablet)produto).isCaneta()));
            produtoElement.appendChild(caneta);

            Element teclado = doc.createElement("teclado");
            teclado.setTextContent(String.valueOf( ((Tablet)produto).isTeclado()));
            produtoElement.appendChild(teclado);

            
        }
        
    }



    private static void writeXml(Document doc, StreamResult result) throws TransformerException {

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();

      // pretty print
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(doc);

      transformer.transform(source, result);

  }
}