package org.example.teste;

import org.example.model.Pedido;
import org.example.service.CategoriaService;
import org.example.service.ClienteService;
import org.example.service.PedidoService;
import org.example.service.ProdutoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TesteNovosDesafios {
    public static void main(String[] args) {
        System.out.println("Teste de novos desafios");
        //todo: implementar novos desafios
        //  PedidoDao
        //  buscaPorData(LocalDate)

//        ClienteService clienteService = new ClienteService(true);
//        clienteService.listarClientesPorNome("Maria").forEach(System.out::println);
//        clienteService.close();

//        ProdutoService produtoService = new ProdutoService(true);
//        produtoService.buscarProdutosPorCategoria("Eletrônicos").forEach(System.out::println);

        PedidoService pedidoService = new PedidoService();
        LocalDate dataInicial = LocalDate.of(2021, 1, 1);
        LocalDate dataFinal = LocalDate.of(2024, 12, 31);

        List<Pedido> pedidos = pedidoService.BuscarPedidosPorData(dataInicial, dataFinal);

        pedidos.forEach(System.out::println);

    }
}
