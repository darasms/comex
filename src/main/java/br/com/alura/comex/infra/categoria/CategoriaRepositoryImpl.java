package br.com.alura.comex.infra.categoria;

import br.com.alura.comex.entity.categoria.Categoria;
import br.com.alura.comex.entity.categoria.CategoriaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final CategoriaDAO categoriaDAO;

    public CategoriaRepositoryImpl(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<Categoria> listarTodasCategorias() {
        return null;
    }

    @Override
    public void cadastrarCategoria(Categoria categoria) {
        categoriaDAO.save(CategoriaEntity.converter(categoria));

    }

    @Override
    public Categoria buscarCategoria(Long id) {

        CategoriaEntity categoriaEntity = categoriaDAO.findById(id).get();

        return Categoria.builder()
                .id(categoriaEntity.getId())
                .nome(categoriaEntity.getNome())
                .status(categoriaEntity.getStatus())
                .produtos(categoriaEntity.toProdutos())
                .build();
    }

    @Override
    public Categoria atualizar(Long id, String categoria) {
        return null;
    }

    @Override
    public Categoria atualizarStatus(Long id) {
        return null;
    }
}
