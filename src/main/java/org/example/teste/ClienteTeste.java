package org.example.teste;

import org.example.model.Cliente;
import org.example.model.Endereco;
import org.example.service.CategoriaService;
import org.example.service.ClienteService;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteTeste {
    ClienteService servico;
    Scanner entrada = new Scanner(System.in);

    public ClienteTeste(boolean usarJpa) {
        servico = new ClienteService(usarJpa);
    }

    public void menu() {
        Integer escolha = -1;
        while (escolha != 0) {
            String menu = """
                      O que voce deseja fazer com a tabela cliente:
                      
                      1 - Listar todos
                      2 - Buscar por ID
                      3 - Cadastrar Cliente
                      4 - Atualizar Cliente
                      5 - Excluir Cliente
                      0 - Sair
                      """;

            System.out.println(menu);

            escolha = entrada.nextInt();
            entrada.nextLine();
            switch (escolha) {
                case 1:
                    LISTAR_CLIENTES();
                    break;
                case 2:
                    BUSCAR_POR_ID();
                    break;
                case 3:
                    CADASTRAR_CLIENTE();
                    break;
                case 4:
                    ATUALIZAR_CLIENTE();
                    break;
                case 5:
                    EXCLUIR_CLIENTE();
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

    private void LISTAR_CLIENTES(){
        List<Cliente> clientes = servico.listarClientes();

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
    private void BUSCAR_POR_ID(){
        System.out.println("Digite o id para busca: ");
        Integer id = entrada.nextInt();
        entrada.nextLine();
        Cliente cliente = servico.buscarPorId(id);
        System.out.println(cliente);
    }
    private void CADASTRAR_CLIENTE(){
        System.out.println("Insira os dados do cliente");
        Cliente cliente = pegaDadosCliente();
        servico.cadastrarCliente(cliente);
    }
    private void ATUALIZAR_CLIENTE(){
        System.out.println("Insira os novos dados do cliente");
        Cliente cliente = pegaDadosCliente();
        servico.atualizarCliente(cliente);
    }
    private void EXCLUIR_CLIENTE(){
        System.out.println("Digite o id para exclusao: ");
        Integer id = entrada.nextInt();
        entrada.nextLine();
        servico.apagarCliente(id);
    }

    private Cliente pegaDadosCliente() {
        System.out.println("Digite o nome: ");
        String nome = entrada.nextLine();
        System.out.println("Digite o cpf: ");
        String cpf = entrada.nextLine();
        System.out.println("Digite o email: ");
        String email = entrada.nextLine();
        System.out.println("Digite o telefone: ");
        String telefone = entrada.nextLine();
        System.out.println("Digite o nome da rua: ");
        String rua = entrada.nextLine();
        System.out.println("Digite o numero da casa: ");
        String numero = entrada.nextLine();
        System.out.println("Digite o bairro: ");
        String bairro = entrada.nextLine();
        System.out.println("Digite a cidade: ");
        String cidade = entrada.nextLine();
        System.out.println("Digite o uf do estado: ");
        String uf = entrada.nextLine();

        return new Cliente(nome, cpf, email, telefone, new Endereco(rua, numero, bairro, cidade, uf));
    }
}
