package org.example.dao;

import org.example.banco.ConnectionFactory;
import org.example.interfaces.IClienteDAO;
import org.example.model.Cliente;
import org.example.model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {
    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM clientes";

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Cliente> listaDeClientes = new ArrayList<>();
            while (resultSet.next()) {
                Cliente cliente = new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        new Endereco(
                                resultSet.getString("rua"),
                                resultSet.getString("numero"),
                                resultSet.getString("bairro"),
                                resultSet.getString("cidade"),
                                resultSet.getString("uf")
                        )
                );

                listaDeClientes.add(cliente);
            }

            resultSet.close();
            preparedStatement.close();
            return listaDeClientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, email, telefone, rua, numero, bairro, cidade, uf)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getEndereco().getRua());
            preparedStatement.setString(6, cliente.getEndereco().getNumero());
            preparedStatement.setString(7, cliente.getEndereco().getBairro());
            preparedStatement.setString(8, cliente.getEndereco().getCidade());
            preparedStatement.setString(9, cliente.getEndereco().getUf());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente buscarPorId(Integer id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Cliente cliente = null;
            if (resultSet.next()) {
                cliente = new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        new Endereco(
                                resultSet.getString("rua"),
                                resultSet.getString("numero"),
                                resultSet.getString("bairro"),
                                resultSet.getString("cidade"),
                                resultSet.getString("uf")
                        )
                );
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarCliente(Cliente cliente){
        String sql = """
                    UPDATE clientes SET
                        nome = ?,
                        cpf = ?,
                        email = ?,
                        telefone = ?,
                        rua = ?,
                        numero = ?,
                        bairro = ?,
                        cidade = ?,
                        uf = ?
                    WHERE id = ?
                    """;

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getEndereco().getRua());
            preparedStatement.setString(6, cliente.getEndereco().getNumero());
            preparedStatement.setString(7, cliente.getEndereco().getBairro());
            preparedStatement.setString(8, cliente.getEndereco().getCidade());
            preparedStatement.setString(9, cliente.getEndereco().getUf());
            preparedStatement.setInt(10, cliente.getId());

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void apagarCliente(Integer id){
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cliente> listarClientesPorNome(String nome) {
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nome + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Cliente> listaDeClientes = new ArrayList<>();
            while (resultSet.next()) {
                Cliente cliente = new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        new Endereco(
                                resultSet.getString("rua"),
                                resultSet.getString("numero"),
                                resultSet.getString("bairro"),
                                resultSet.getString("cidade"),
                                resultSet.getString("uf")
                        )
                );

                listaDeClientes.add(cliente);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return listaDeClientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
