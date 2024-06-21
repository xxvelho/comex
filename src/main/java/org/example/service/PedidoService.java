package org.example.service;

import org.example.dao.jpa.JpaPedidoDAO;
import org.example.interfaces.IPedidoDAO;
import org.example.model.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoService {
    IPedidoDAO pedidoDAO;
    EntityManagerFactory factory;
    EntityManager manager;

    public PedidoService(){
        factory = Persistence.createEntityManagerFactory("comex");
        manager = factory.createEntityManager();
        pedidoDAO = new JpaPedidoDAO(manager);
    }

    public Pedido buscaPedidoPorId(Integer id) {
        return pedidoDAO.buscaPedidoPorId(id);
    }

    public void cadastraPedido(Pedido pedido) {
        pedidoDAO.cadastraPedido(pedido);
    }

    public List<Pedido> listaTodosPedidos() {
        return pedidoDAO.listaTodosPedidos();
    }

    public List<Pedido> BuscarPedidosPorData(LocalDate dataInicial, LocalDate dataFinal) {
        return pedidoDAO.BuscarPedidosPorData(dataInicial, dataFinal);
    }

    public void close() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }
}
