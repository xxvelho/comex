package org.example.dao;

import org.example.banco.ConnectionFactory;
import org.example.interfaces.IProdutoDAO;
import org.example.model.Categoria;
import org.example.model.Produto;
import org.example.service.CategoriaService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO {
    public void cadastrarProduto(Produto produto){
        String sql = "INSERT INTO produtos (nome, descricao, preco, categoria_id) VALUES (?, ?, ?, ?)";


        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getCategoria().getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listarProdutos(){
        String sql = "SELECT * FROM produtos";

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Produto> listaDeProdutos = new ArrayList<>();
            while (resultSet.next()){
                Produto produto = new Produto(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getDouble("preco"),
                        new CategoriaService(false).buscarCategoriaPorId(resultSet.getInt("categoria_id"))
                );

                listaDeProdutos.add(produto);
            }

            resultSet.close();
            preparedStatement.close();
            return listaDeProdutos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> listarProdutosPorNomeCrescente(){
        String sql = "SELECT nome FROM produtos ORDER BY nome ASC";

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> listaDeProdutos = new ArrayList<>();
            while (resultSet.next()){
                String nome = resultSet.getString("nome");

                listaDeProdutos.add(nome);
            }

            resultSet.close();
            preparedStatement.close();
            return listaDeProdutos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarProduto(Produto produto){
        String sql = """
                     UPDATE produtos SET 
                        nome = ?, 
                        descricao = ?, 
                        preco = ?, 
                        categoria_id = ? 
                     WHERE id = ?
                     """;

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getCategoria().getId());
            preparedStatement.setInt(5, produto.getId());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirProduto(Integer id){
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Produto buscarProdutoPorId(Integer id) {
        return null;
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(String nome) {
        String sql = "SELECT * FROM produtos WHERE LIKE ?";
        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%" + nome + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Produto> listaDeProdutos = new ArrayList<>();
            while (resultSet.next()){
                Produto produto = new Produto(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getDouble("preco"),
                        new CategoriaService(false).buscarCategoriaPorId(resultSet.getInt("id"))
                );

                listaDeProdutos.add(produto);
            }

            resultSet.close();
            preparedStatement.close();
            return listaDeProdutos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
