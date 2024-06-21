package org.example.interfaces;

import org.example.model.Categoria;
import org.example.model.Produto;

import java.util.List;

public interface IProdutoDAO {
    void cadastrarProduto(Produto produto);
    List<Produto> listarProdutos();
    List<String> listarProdutosPorNomeCrescente();
    void atualizarProduto(Produto produto);
    void excluirProduto(Integer id);
    Produto buscarProdutoPorId(Integer id);
    List<Produto> buscarProdutosPorCategoria(String nome);

}
