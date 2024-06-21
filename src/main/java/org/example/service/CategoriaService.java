package org.example.service;

import org.example.dao.CategoriaDAO;
import org.example.dao.jpa.JpaCategoriaDAO;
import org.example.interfaces.ICategoriaDAO;
import org.example.model.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CategoriaService {
    ICategoriaDAO categoriaDAO;
    EntityManagerFactory factory;
    EntityManager manager;

    public CategoriaService(boolean usarJpa) {
        if (usarJpa){
            factory = Persistence.createEntityManagerFactory("comex");
            manager = factory.createEntityManager();
            categoriaDAO = new JpaCategoriaDAO(manager);
        } else{
            categoriaDAO = new CategoriaDAO();
        }
    }

    public void cadastarCategoria(Categoria categoria){
        this.categoriaDAO.cadastrarCategoria(categoria);
    }

    public List<Categoria> listarCategorias(){
        return this.categoriaDAO.listarCategorias();
    }

    public List<String> listarNomesOrdenadoCrescente(){
        return this.categoriaDAO.listarNomesOrdenadoCrescente();
    }

    public void atualizarCategoria(Categoria categoria){
        this.categoriaDAO.atualizarCategoria(categoria);
    }

    public void excluirCategoria(Integer id){
        this.categoriaDAO.apagarCategoria(id);
    }

    public Categoria buscarCategoriaPorId(Integer id) {
        return this.categoriaDAO.buscarCategoriaPorId(id);
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
