package org.example.interfaces;

import org.example.model.Cliente;

import java.util.List;

public interface IClienteDAO {
    void cadastrarCliente(Cliente cliente);
    List<Cliente> listarClientes();
    Cliente buscarPorId(Integer id);
    void atualizarCliente(Cliente cliente);
    void apagarCliente(Integer id);

    List<Cliente> listarClientesPorNome(String nome);

}
