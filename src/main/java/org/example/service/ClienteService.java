package org.example.service;

import org.example.dao.ClienteDAO;
import org.example.dao.jpa.JpaClienteDAO;
import org.example.interfaces.IClienteDAO;
import org.example.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClienteService {
    IClienteDAO clienteDAO;
    EntityManagerFactory factory;
    EntityManager manager;

    public ClienteService(boolean usarJpa) {
        if (usarJpa){
            factory = Persistence.createEntityManagerFactory("comex");
            manager = factory.createEntityManager();
            clienteDAO = new JpaClienteDAO(manager);
        } else{
            clienteDAO = new ClienteDAO();
        }
    }

    public void cadastrarCliente(Cliente cliente){
        this.clienteDAO.cadastrarCliente(cliente);
    }

    public List<Cliente> listarClientes(){
        return this.clienteDAO.listarClientes();
    }

    public Cliente buscarPorId(Integer id){
        return this.clienteDAO.buscarPorId(id);
    }

    public void atualizarCliente(Cliente cliente){
        this.clienteDAO.atualizarCliente(cliente);
    }

    public List<Cliente> listarClientesPorNome(String nome) {
        return this.clienteDAO.listarClientesPorNome(nome);
    }

    public void apagarCliente(Integer id){
        this.clienteDAO.apagarCliente(id);
    }

    public void close() {
        if (manager != null){
            manager.close();
        }
        if (factory != null){
            factory.close();
        }
    }
}
