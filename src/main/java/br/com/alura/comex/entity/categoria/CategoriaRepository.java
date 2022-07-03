package br.com.alura.comex.entity.categoria;

import java.util.List;

public interface CategoriaRepository {

    List<Categoria> listarTodasCategorias();

    void cadastrarCategoria(Categoria categoria);

    void deletarCategoria(Long id);

    Categoria buscarCategoria(Long id);

    Categoria atualizar(Long id, String nomeCategoria);

    Categoria atualizarStatus(Long id);

}
