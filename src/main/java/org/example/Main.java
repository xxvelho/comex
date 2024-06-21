package org.example;

import org.example.teste.CategoriaTeste;
import org.example.teste.ClienteTeste;
import org.example.teste.ProdutoTeste;

import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        boolean usaJpa = usarJDBCouJPA();
        Integer escolha = -1;
        while (escolha != 0){
        String menu = """
                      O que voce deseja fazer:
                        
                      1 - Trabalhar com Clientes
                      2 - Trabalhar com Produtos
                      3 - Trabalhar com Categorias
                      0 - Sair
                      """;

        System.out.println(menu);
        escolha = entrada.nextInt();
        entrada.nextLine();
            switch (escolha) {
                case 1:
                    ClienteTeste clienteTeste = new ClienteTeste(usaJpa);
                    clienteTeste.menu();
                    break;
                case 2:
                    ProdutoTeste produtoTeste = new ProdutoTeste(usaJpa);
                    produtoTeste.menu();
                    break;
                case 3:
                    CategoriaTeste categoriaTeste = new CategoriaTeste(usaJpa);
                    categoriaTeste.menu();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opcao fora do escopo do menu!!");
                    break;
            }
        }
    }

    private static boolean usarJDBCouJPA() {
        System.out.println("Digite 1 para usar JPA ou 2 para usar JDBC: ");
        Integer escolha = entrada.nextInt();
        entrada.nextLine();
        if (escolha == 1){
            return true;
        } else{
            return false;
        }
    }
}