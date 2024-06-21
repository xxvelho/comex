package org.example.dao.jpa;

import org.example.exception.RegraDeNegocioException;
import org.example.interfaces.IProdutoDAO;
import org.example.model.Categoria;
import org.example.model.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaProdutoDAO implements IProdutoDAO {
    private EntityManager manager;

    public JpaProdutoDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        manager.getTransaction().begin();
        manager.persist(produto);
        manager.getTransaction().commit();
    }

    @Override
    public List<Produto> listarProdutos() {
        return manager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    @Override
    public List<String> listarProdutosPorNomeCrescente() {
        return manager.createQuery("SELECT p.nome FROM Produto p ORDER BY p.nome ASC", String.class).getResultList();
    }

    @Override
    public void atualizarProduto(Produto produto) {
        Produto produtoEncontrado = manager.find(Produto.class, produto.getId());
        if (produtoEncontrado == null) {
            throw new RegraDeNegocioException("Produto não encontrado");
        } else {
            Categoria categoriaEncontrada = manager.find(Categoria.class, produto.getCategoria().getId());
            if (categoriaEncontrada == null) {
                throw new RegraDeNegocioException("Categoria para atualizar produto está registrada no banco de dados");
            } else {
                produtoEncontrado.setNome(produto.getNome());
                produtoEncontrado.setDescricao(produto.getDescricao());
                produtoEncontrado.setPreco(produto.getPreco());
                produtoEncontrado.setCategoria(categoriaEncontrada);
                manager.getTransaction().begin();
                manager.merge(produtoEncontrado);
                manager.getTransaction().commit();
            }
        }

    }

    @Override
    public Produto buscarProdutoPorId(Integer id) {
        return manager.find(Produto.class, id);
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(String nome) {
        return manager.createQuery("SELECT p FROM Produto p WHERE p.categoria.nome = :nome", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    @Override
    public void excluirProduto(Integer id) {
        Produto produto = buscarProdutoPorId(id);
        if (produto == null) {
            throw new RegraDeNegocioException("Produto não encontrado");
        } else {
            manager.getTransaction().begin();
            manager.remove(produto);
            manager.getTransaction().commit();
        }

    }
}
