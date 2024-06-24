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
// Exemplo de uso do Singleton Pattern
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

********************************

Tratamento de Exceções
O sistema trata exceções de forma a garantir a integridade dos dados e a experiência do usuário. Essas exceções são tratadas na leitura dos arquivos.


Leitura e Gravação de Arquivos
O sistema lê e grava informações importantes em arquivos, garantindo a persistência dos dados entre as execuções. 