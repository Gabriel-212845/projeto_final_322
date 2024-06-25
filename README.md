# Projeto Final de Programação Orientada a Objetos

## Descrição do Projeto
Projeto desenvolvido para a disciplina de Programação Orientada a Objetos e simula o funcionamento de uma loja. O sistema inclui funcionalidades para gerenciamento de funcionários, gestores e clientes, além de uma interface de compras e controle de estoque. A proposta do software é fornecer uma solução integrada e eficiente para a gestão de lojas, facilitando o controle de produtos, vendas e funcionários através de uma interface amigável e intuitiva.


Proposta do Software
A ideia por trás do projeto é criar uma aplicação que simule as operações de uma loja, desde o cadastro de produtos até a realização de vendas e o gerenciamento de estoque. O sistema permite que diferentes tipos de usuários (gestores, funcionários e clientes) interajam com a plataforma, realizando tarefas específicas conforme suas permissões. O sistema de login garante a segurança e integridade dos dados, permitindo que apenas usuários autorizados acessem determinadas funcionalidades.


Funcionalidades
Cadastro de Produtos: Permite adicionar, editar e remover produtos do estoque.
Gerenciamento de Estoque: Monitora a quantidade de produtos disponíveis.
Sistema de Vendas: Realiza vendas, aplicando descontos e calculando o total a ser pago pelo cliente.
Cadastro de Clientes: Gerencia informações dos clientes, facilitando a realização de vendas e o controle de histórico de compras.
Cadastro de Funcionários e Gestores: Permite o gerenciamento de usuários do sistema, incluindo permissões e funções.
Sistema de Login: Garante que apenas usuários autenticados possam acessar determinadas funcionalidades do sistema.
Interface Gráfica: Controla o sistema através de uma interface gráfica amigável, facilitando a interação do usuário com o software.
Leitura e Gravação de Arquivos: Salva dados importantes, como informações de produtos e clientes, em arquivos para garantir a persistência dos dados.

Relacionamentos (Associação, Agregação ou Composição)
O sistema implementa vários tipos de relacionamentos:
Associação: Entre Vendas e Clientes, onde cada venda está associada a um cliente.
Agregação: Entre Gestores e Funcionarios, onde gestores gerenciam funcionários.
Composição: Entre Pedido e Produtos, onde um pedido é composto por múltiplos produtos.

Interfaces e Classes Abstratas
Interfaces:
Comprar: Define métodos comuns para todos os tipos de usuários (clientes, funcionários, gestores) realizarem as compras.
Estocar: Interface para funcionários e gestores manipularem o controle de estoque.
Gerir: Interface para o gestor controlar o time de funcionários e realizar os pedidos de compra do estoque.

Classes Abstratas:
Pessoas: Classe abstrata que serve como base para diferentes tipos de usuários, com informações como nome, id, saldo e outros pontos comuns entre os diferentes tipos de usuários.
Produtos: Classe abstrata que serve como base para diferentes tipos de produtos, com informações como nome, marca, preço e outros pontos comuns entre os diferentes tipos de produtos.


Interface Gráfica
O sistema foi desenvolvido com uma interface gráfica, facilitando a interação do usuário com as funcionalidades do sistema. A interface gráfica foi construída utilizando JavaFX.

Design Patterns Implementados
Singleton Pattern:
Implementado para garantir que apenas uma instância do banco de dados seja criada, proporcionando um ponto de acesso global a essa instância.
-- Exemplo de uso do Singleton Pattern
public class Estoque {
    private static Estoque instancia;

    private Estoque (List<Produtos> produtosNoEstoque, List<Integer> quantDoProduto){
        this.produtosNoEstoque = produtosNoEstoque;
        this.quantDoProduto = quantDoProduto;
    }

    public static Estoque getInstance(List<Produtos> produtosNoEstoque, List<Integer> quantDoProduto){
        if(instancia == null){
            instancia = new Estoque(produtosNoEstoque, quantDoProduto);
        }
        return instancia;
    }
}

Prototype Pattern:
Implementado para criar novos objetos baseados em objetos existentes, evitando a criação direta através de construtores.
-- Exemplo de uso do Prototype Pattern
    @Override
    public Produtos clone() throws CloneNotSupportedException {
        return (Produtos) super.clone();
    }

    @Override
    public Celular clone() throws CloneNotSupportedException {
        return (Celular) super.clone();
    }

Observer Pattern:
Implementado para criar uma relação de dependência entre os objetos. Com isso, caso um objeto seja alterado, seus dependentes serão informados
-- Exemplo de uso do Observer Pattern
    public void adicionarObservador(Cliente cliente) {
        observadores.add(cliente);
    }

    public void removerObservador(Cliente cliente) {
        observadores.remove(cliente);
    }

    public void notificarObservadores() {
        for (Cliente cliente : observadores) {
            cliente.atualizar(this);
        }
    }


Tratamento de Exceções
O sistema trata exceções de forma a garantir a integridade dos dados e a experiência do usuário. Essas exceções são tratadas na leitura dos arquivos.


Leitura e Gravação de Arquivos
O sistema lê e grava informações importantes em arquivos, garantindo a persistência dos dados entre as execuções.


Documentação das Classes e Funções
->Classe 'Pessoas'
*Associação com Compra
*Implementa a interface Comprar para que todas as pessoas tenham os métodos de compra
*Atributos 'protected' para que sejam compartilhados apenas por herança
*Informações gerais como nome, id, saldo, carrinho, histórico de compras (lista de Compra) e compras em transito (lista de Compra)
*Gets para obter as informações gerais
*Como todas as pessoas podem acessar o módulo de compra, inclui métodos como 'adicionarAoCarrinho' que recebe um produto e a quantidade do mesmo para adicionar ao carrinho.
*Da mesma forma, também pode 'removerDoCarrinho'
*'comprar()' - Método para validar a compra

->Classe 'Cliente'
*Herda a classe 'Pessoas'
*gets e sets
*'pedirDesconto()' - Método para solicitar desconto para a compra a ser efetuada. Funciona de forma randomica, retornando até 5% de desconto.

->Classe 'Caixa'
*Herda a classe 'Pessoas'
*Implementa a interface Estocar
*Atributos 'dataDeContratacao' (para aumento de salário após 1 ano), 'salário', 'faltasSemJust' (para que o gestor avalie o funcionário), agregação com a classe 'PedidoDeAumento' (cada funcionário pode solicitar um pedido de aumento de salário), histórico de aumento de salário, lista de PedidoDeEstoque para despachar os pedidos
*gets e sets
*Métodos para adicionar cliente a relação dos pedidos de compra.
*'adicionarPedidoDeEstoque' para solicitar que seja feito uma compra de reposição de estoque
*'adicionarAumentoNoHistorico' para controle dos pedidos de aumento de salário
*'pedidosAguardandoEnvio' exibe a lista com os pedidos pendentes de liberação
*'enviarPedidos' para validar o envio das compras

->Classe 'Gerente'
*Herda a classe 'Pessoas'
*Implementa a interface Gerir
*Além dos atributos de pessoas, também controla todo o caixa da empresa e os funcionários
*'cofre' e 'lucro' são referentes a loja como um todo.
*Lista de caixasContratados
*Lista com o histórico de rendimento mensal
*gets e sets
*'colocarNoCofre' para aumentar o caixa da empresa
*'gastarDoCofre' para retirar do caixa da empresa
*'contratarCaixa' e 'demitirCaixa' para gestão dos funcionários
*'analisarPedidosDeAumento' para confirmar ou negar o pedido de aumento de salário do funcionário
*Métodos para controle de fundos da loja e funcionários ('calcularDespesaDeFuncionario', 'calcularDespesaDeProduto', 'calcularRendimentoDoMes', 'pagarDespesas')
*Agregação com classe 'PedidoDeEstoque' que possui os produtos e as quantidades compradas para repor o estoque da loja
*'solicitarCompra' para solicitar um Pedido de Estoque

->Classe 'Produtos'
*Associação com Estoque
*Classe base de todos os tipos de produtos, com atributos gerais como preço de custo, preço de venda, nome, marca, fornecedor, se o pedido está em promoção e o caminho para a foto do produto
*gets e sets
*Método para validar se está com promoção
*Método para copiar a classe

->Classe 'Celular'
*Herda a classe 'Produtos'
*Atributos específicos como tamanho da tela, quantidade de chips, armazenamento interno, resolução da camera, capacidade da bateria e as resistências do aparelho (se houver)
*gets e sets

->Classe 'Tablet'
*Herda a classe 'Produtos'
*Atributos específicos como tamanho da tela, armazenamento interno, resolução da camera, capacidade da bateria, atributos para indicar se acompanha caneta e se acompanha teclado
*gets e sets
*Validação se tem caneta e teclado

->Classe 'TV'
*Herda a classe 'Produtos'
*Atributos específicos como tamanho da tela, resolução da tela. tipo da tela, valor de consumo de energia, atributos para indicar se é um modelo smart e se acompanha suporte de instalação
*gets e sets
*Validação se é smart e se tem suporte

->Classe 'Compra'
*Associação com 'Produtos' e 'Pessoas'
*Lista de produtos e Lista de quantidade, os dois atributos funcionam como uma "matriz"
*valor de desconto
*valor pago no final
*datas do pedido e do pagamento
*gets e sets
*'adicionarACompra' validação para adicionar o produto
*'removerDaCompra' validação para remover o produto
*'atualizarPrecoPago' para atualizar o preco final após inserir ou remover um produto

->Classe 'Estoque'
*Associação com 'Produtos'
*Lista de produtos e Lista de quantidade, os dois atributos funcionam como uma "matriz"
*gets e sets
*'adicionarAoEstoque' e 'removerDoEstoque' para manipulação do estoque
*'getInstance' para instanciar o estoque utilizando o design pattern Singleton
