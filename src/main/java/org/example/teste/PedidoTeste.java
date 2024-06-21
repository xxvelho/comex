package org.example.teste;

import org.example.service.PedidoService;

import java.util.Scanner;

public class PedidoTeste {
    PedidoService service;

    Scanner entrada = new Scanner(System.in);

    public void menu() {
        Integer escolha = -1;
        while (escolha != 0) {
            String menu = """
                    O que voce deseja fazer com a tabela pedido:
                    
                    1 - Listar todos
                    2 - Listar Pedidos por Data
                    3 - Cadastrar Pedido
                    0 - Sair
                    """;
            System.out.println(menu);
            escolha = entrada.nextInt();
            entrada.nextLine();

            switch (escolha) {
                case 1:
                    LISTAR_PEDIDOS();
                    break;
                case 2:
//                    LISTAR_PEDIDOS_POR_DATA();
                    break;
                case 3:
                    CADASTRAR_PEDIDO();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    service.close();
                    return;
                default:
                    System.out.println("Opcao fora do escopo do menu!");
                    break;
            }
            System.out.println("\nPressione enter para continuar...");
            entrada.nextLine();
        }
    }

    private void LISTAR_PEDIDOS() {
        service.listaTodosPedidos().forEach(System.out::println);
    }

//    private void LISTAR_PEDIDOS_POR_DATA() {
//        System.out.println("Digite a data no formato dd/MM/yyyy");
//        String data = entrada.nextLine();
//        service.BuscarPedidosPorData(data).forEach(System.out::println);
//    }

    private void CADASTRAR_PEDIDO() {

    }
}
