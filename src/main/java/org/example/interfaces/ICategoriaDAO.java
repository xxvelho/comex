package org.example.interfaces;

import org.example.model.Categoria;

import java.util.List;

public interface ICategoriaDAO {
    void cadastrarCategoria(Categoria categoria);
    List<Categoria> listarCategorias();
    Categoria buscarCategoriaPorId(Integer id);
    void atualizarCategoria(Categoria categoria);
    void apagarCategoria(Integer id);
    List<String> listarNomesOrdenadoCrescente();


}
