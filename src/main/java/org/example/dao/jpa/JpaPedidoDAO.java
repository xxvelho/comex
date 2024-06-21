package org.example.dao.jpa;

import org.example.interfaces.IPedidoDAO;
import org.example.model.Pedido;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class JpaPedidoDAO implements IPedidoDAO {
    EntityManager manager;

    public JpaPedidoDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Pedido buscaPedidoPorId(Integer id) {
        return manager.find(Pedido.class, id);
    }

    @Override
    public void cadastraPedido(Pedido pedido) {
        manager.getTransaction().begin();
        manager.persist(pedido);
        manager.getTransaction().commit();
    }

    @Override
    public List<Pedido> listaTodosPedidos() {
        return manager.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }

    @Override
    public List<Pedido> BuscarPedidosPorData(LocalDate dataInicial, LocalDate dataFinal) {
        LocalDateTime dataHoraInical = dataInicial.atTime(LocalTime.MIN);
        LocalDateTime dataHoraFinal = dataFinal.atTime(LocalTime.MAX);
        return manager.createQuery("SELECT p FROM Pedido p WHERE p.dataHora > :dataHoraInical AND p.dataHora < :dataHoraFinal ", Pedido.class)
                .setParameter("dataHoraInical", dataHoraInical)
                .setParameter("dataHoraFinal", dataHoraFinal)
                .getResultList();
    }
}
