package org.example.dao;

import org.example.banco.ConnectionFactory;
import org.example.interfaces.ICategoriaDAO;
import org.example.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements ICategoriaDAO {
    @Override
    public List<Categoria> listarCategorias(){
        String sql = "SELECT * FROM categorias";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.recuperarConexao()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Categoria> listaDeCategorias = new ArrayList<>();
            while (resultSet.next()){
                Categoria categoria = new Categoria(
                        resultSet.getInt("id"),
                        resultSet.getString("nome")
                );

                listaDeCategorias.add(categoria);
            }

            preparedStatement.close();
            resultSet.close();
            return listaDeCategorias;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cadastrarCategoria(Categoria categoria){
        String sql = "INSERT INTO categorias (nome) VALUES (?)";

        PreparedStatement preparedStatement;
        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getNome());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> listarNomesOrdenadoCrescente(){
        String sql = "SELECT nome FROM categorias ORDER BY nome ASC";

        PreparedStatement preparedStatement;
        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> listaDeCategorias = new ArrayList<>();
            while (resultSet.next()){
                String nome = resultSet.getString("nome");
                listaDeCategorias.add(nome);
            }
            resultSet.close();
            preparedStatement.close();
            return listaDeCategorias;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizarCategoria(Categoria categoria){
        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";

        PreparedStatement preparedStatement;
        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getNome());
            preparedStatement.setInt(2, categoria.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void apagarCategoria(Integer id){
        String sql = "DELETE FROM categorias WHERE id = ?";

        PreparedStatement preparedStatement;
        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Categoria buscarCategoriaPorId(Integer id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        PreparedStatement preparedStatement;

        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Categoria categoria = null;
            if(resultSet.next()){
                categoria = new Categoria(resultSet.getInt("id"), resultSet.getString("nome"));
            }
            preparedStatement.close();
            resultSet.close();
            return categoria;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
