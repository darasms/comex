package br.com.alura.comex.entity.categoria;

import java.util.List;

public interface CategoriaRepository {

    List<Categoria> listarTodasCategorias();

    void cadastrarCategoria(Categoria categoria);

    Categoria buscarCategoria(Long id);

    Categoria atualizar(Long id, String categoria);

    Categoria atualizarStatus(Long id);

}
