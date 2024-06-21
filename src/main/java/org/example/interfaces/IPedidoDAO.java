package org.example.interfaces;

import org.example.model.Pedido;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IPedidoDAO {
    Pedido buscaPedidoPorId(Integer id);
    void cadastraPedido(Pedido pedido);
    List<Pedido> listaTodosPedidos();

    List<Pedido> BuscarPedidosPorData(LocalDate dataInicial, LocalDate dataFinal);
}
