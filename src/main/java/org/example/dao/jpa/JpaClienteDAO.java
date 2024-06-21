package org.example.dao.jpa;

import org.example.exception.RegraDeNegocioException;
import org.example.interfaces.IClienteDAO;
import org.example.model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaClienteDAO implements IClienteDAO {
    private EntityManager manager;

    public JpaClienteDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastrarCliente(Cliente cliente) {
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();
    }

    @Override
    public List<Cliente> listarClientes() {
        return manager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return manager.find(Cliente.class, id);
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        Cliente clienteEncontrado = manager.find(Cliente.class, cliente.getId());
        if (clienteEncontrado == null) {
            throw new RegraDeNegocioException("Cliente não encontrado");
        } else {
            clienteEncontrado.setNome(cliente.getNome());
            clienteEncontrado.setCpf(cliente.getCpf());
            clienteEncontrado.setTelefone(cliente.getTelefone());
            clienteEncontrado.setTelefone(cliente.getTelefone());
            clienteEncontrado.setEndereco(cliente.getEndereco());

            manager.getTransaction().begin();
            manager.merge(clienteEncontrado);
            manager.getTransaction().commit();
        }
    }

    @Override
    public void apagarCliente(Integer id) {
        Cliente cliente = manager.find(Cliente.class, id);
        if (cliente == null) {
            throw new RegraDeNegocioException("Cliente não encontrado");
        } else {
            manager.getTransaction().begin();
            manager.remove(cliente);
            manager.getTransaction().commit();
        }
    }

    @Override
    public List<Cliente> listarClientesPorNome(String nome) {
        return manager.createQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :nome", Cliente.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}
