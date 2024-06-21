package org.example.teste;

import org.example.model.Categoria;
import org.example.service.CategoriaService;

import java.util.List;
import java.util.Scanner;

public class CategoriaTeste {
    CategoriaService servico;
    Scanner entrada = new Scanner(System.in);

    public CategoriaTeste(boolean usarJpa) {
        servico = new CategoriaService(usarJpa);
    }

    public void menu() {
        Integer escolha = -1;
        while (escolha != 0) {
            String menu = """
                    O que voce deseja fazer com a tabela categoria:
                    
                    1 - Listar todos
                    2 - Listar Nomes das Categorias Ordenado Crescente
                    3 - Cadastrar Categoria
                    4 - Atualizar Categoria
                    5 - Excluir Categoria
                    0 - Sair
                    """;
            System.out.println(menu);

            escolha = entrada.nextInt();
            entrada.nextLine();
            switch (escolha) {
                case 1:
                    LISTAR_CATEGORIAS();
                    break;
                case 2:
                    LISTAR_NOMES_ORDENADO_CRESCENTE();
                    break;
                case 3:
                    CADASTRAR_CATEGORIA();
                    break;
                case 4:
                    ATUALIZAR_CATEGORIA();
                    break;
                case 5:
                    EXCLUIR_CATEGORIA();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    servico.close();
                    return;
                default:
                    System.out.println("Opcao fora do escopo do menu!");
                    break;
            }

            System.out.println("\nPressione ENTER para continuar...");
            entrada.nextLine();
        }
    }

    private void EXCLUIR_CATEGORIA() {
        System.out.println("Digite o id da categoria para a exclusao: ");
        Integer id = entrada.nextInt();
        entrada.nextLine();

        servico.excluirCategoria(id);
    }

    private void ATUALIZAR_CATEGORIA() {
        System.out.println("Digite o id da categoria que vc deseja atualizar: ");
        Integer id = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite o novo nome da categoria: ");
        String nome = entrada.nextLine();
        servico.atualizarCategoria(new Categoria(id, nome));
    }

    private void CADASTRAR_CATEGORIA() {
        System.out.println("Digite o nome da categoria: ");
        String nome = entrada.nextLine();
        servico.cadastarCategoria(new Categoria(nome));
    }

    private void LISTAR_NOMES_ORDENADO_CRESCENTE() {
        List<String> categorias = servico.listarNomesOrdenadoCrescente();
        categorias.forEach(System.out::println);
    }

    private void LISTAR_CATEGORIAS() {
        List<Categoria> categorias = servico.listarCategorias();
        categorias.forEach(System.out::println);
    }
}
