package org.example.teste;

import org.example.model.Categoria;
import org.example.model.Produto;
import org.example.service.CategoriaService;
import org.example.service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class ProdutoTeste {
    ProdutoService service;
    Scanner entrada = new Scanner(System.in);
    private boolean usarJpa;

    public ProdutoTeste(boolean usarJpa) {
        service = new ProdutoService(usarJpa);
        this.usarJpa = usarJpa;
    }

    public void menu() {
        Integer escolha = -1;
        while (escolha != 0) {
            String menu = """
                    O que voce deseja fazer com a tabela produto:
                    
                    1 - Listar todos
                    2 - Listar Produtos por Nome Crescente
                    3 - Cadastrar Produto
                    4 - Atualizar Produto
                    5 - Excluir Produto
                    0 - Sair
                    """;
            System.out.println(menu);
            escolha = entrada.nextInt();
            entrada.nextLine();

            switch (escolha) {
                case 1:
                    LISTAR_PRODUTOS();
                    break;
                case 2:
                    LISTAR_PRODUTOS_POR_NOME_CRESCENTE();
                    break;
                case 3:
                    CADASTRAR_PRODUTO();
                    break;
                case 4:
                    ATUALIZAR_PRODUTO();
                    break;
                case 5:
                    EXCLUIR_PRODUTO();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    service.close();
                    return;
                default:
                    System.out.println("Opcao fora do escopo do menu!");
                    break;
            }
            System.out.println("\nPressione ENTER para continuar...");
            entrada.nextLine();
        }
    }

    private void EXCLUIR_PRODUTO() {
        System.out.println("Digite o id do produto para a exclusao: ");
        Integer id = entrada.nextInt();
        entrada.nextLine();
        service.excluirProduto(id);
    }

    private void ATUALIZAR_PRODUTO() {
        System.out.println("Digite o id do produto que vc deseja atualizar: ");
        Integer id = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite o nome do produto: ");
        String nome = entrada.nextLine();
        System.out.println("Digite a descricao do produto: ");
        String descricao = entrada.nextLine();
        System.out.println("Digite o preco do produto: ");
        Double preco = entrada.nextDouble();
        entrada.nextLine();
        System.out.println("Digite o id da categoria do produto: ");
        Integer idCategoria = entrada.nextInt();
        entrada.nextLine();

        CategoriaService categoriaService = new CategoriaService(usarJpa);
        Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);
        Produto produto = new Produto(id, nome, descricao, preco, categoria);
        service.atualizarProduto(produto);
    }

    private void CADASTRAR_PRODUTO() {
        System.out.println("Digite o nome do produto: ");
        String nome = entrada.nextLine();
        System.out.println("Digite a descricao do produto: ");
        String descricao = entrada.nextLine();
        System.out.println("Digite o preco do produto: ");
        Double preco = entrada.nextDouble();
        entrada.nextLine();
        System.out.println("Digite o id da categoria do produto: ");
        Integer idCategoria = entrada.nextInt();
        entrada.nextLine();

        CategoriaService categoriaService = new CategoriaService(usarJpa);
        Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);
        Produto produto = new Produto(nome, descricao, preco, categoria);
        service.cadastarProduto(produto);
    }

    private void LISTAR_PRODUTOS_POR_NOME_CRESCENTE() {
        List<String> produtos = service.listarProdutosPorNomeCrescente();
        produtos.forEach(System.out::println);
    }

    private void LISTAR_PRODUTOS() {
        List<Produto> produtos = service.listarProdutos();
        produtos.forEach(System.out::println);
    }
}
