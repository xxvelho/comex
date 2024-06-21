package org.example.service;

import org.example.dao.ProdutoDAO;
import org.example.dao.jpa.JpaProdutoDAO;
import org.example.interfaces.IProdutoDAO;
import org.example.model.Categoria;
import org.example.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProdutoService {
    IProdutoDAO produtoDAO;
    EntityManagerFactory factory;
    EntityManager manager;

    public ProdutoService(boolean usarJpa) {
        if (usarJpa){
            factory = Persistence.createEntityManagerFactory("comex");
            manager = factory.createEntityManager();
            produtoDAO = new JpaProdutoDAO(manager);
        } else{
            produtoDAO = new ProdutoDAO();
        }
    }

    public void cadastarProduto(Produto produto){
        this.produtoDAO.cadastrarProduto(produto);
    }

    public List<Produto> listarProdutos(){
        return this.produtoDAO.listarProdutos();
    }

    public List<String> listarProdutosPorNomeCrescente(){
        return this.produtoDAO.listarProdutosPorNomeCrescente();
    }

    public void atualizarProduto(Produto produto){
        this.produtoDAO.atualizarProduto(produto);
    }

    public void excluirProduto(Integer id){
        this.produtoDAO.excluirProduto(id);
    }

    public List<Produto> buscarProdutosPorCategoria(String nome){
        return this.produtoDAO.buscarProdutosPorCategoria(nome);
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
