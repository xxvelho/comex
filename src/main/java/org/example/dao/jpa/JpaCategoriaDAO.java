package org.example.dao.jpa;

import org.example.exception.RegraDeNegocioException;
import org.example.interfaces.ICategoriaDAO;
import org.example.model.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaCategoriaDAO implements ICategoriaDAO {
    private EntityManager manager;

    public JpaCategoriaDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastrarCategoria(Categoria categoria) {
        manager.getTransaction().begin();
        manager.persist(categoria);
        manager.getTransaction().commit();
    }

    @Override
    public List<Categoria> listarCategorias() {
        return manager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    @Override
    public Categoria buscarCategoriaPorId(Integer id) {
        return manager.find(Categoria.class, id);
    }

    @Override
    public void atualizarCategoria(Categoria categoria) {
        Categoria categoriaEncontrada = buscarCategoriaPorId(categoria.getId());
        if (categoriaEncontrada == null) {
            throw new RegraDeNegocioException("Categoria não encontrada");
        } else {
            categoriaEncontrada.setNome(categoria.getNome());
            manager.getTransaction().begin();
            manager.merge(categoriaEncontrada);
            manager.getTransaction().commit();
        }
    }

    @Override
    public void apagarCategoria(Integer id) {
        Categoria categoria = buscarCategoriaPorId(id);
        if (categoria == null) {
            throw new RegraDeNegocioException("Categoria não encontrada");
        } else {
            manager.getTransaction().begin();
            manager.remove(categoria);
            manager.getTransaction().commit();
        }

    }

    @Override
    public List<String> listarNomesOrdenadoCrescente() {
        return manager.createQuery("SELECT c.nome FROM Categoria c ORDER BY c.nome ASC", String.class).getResultList();
    }
}