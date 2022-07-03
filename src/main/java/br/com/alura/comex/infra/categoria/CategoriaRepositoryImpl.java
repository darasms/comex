package br.com.alura.comex.infra.categoria;

import br.com.alura.comex.entity.categoria.Categoria;
import br.com.alura.comex.entity.categoria.CategoriaRepository;
import br.com.alura.comex.infra.enuns.StatusCategoria;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final CategoriaDAO categoriaDAO;

    public CategoriaRepositoryImpl(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<Categoria> listarTodasCategorias() {
        List<CategoriaEntity> categoria = categoriaDAO.findAll();

        return categoria.stream().map(CategoriaEntity::toCategoria).collect(Collectors.toList());
    }

    @Override
    public Categoria cadastrarCategoria(Categoria categoria) {
        CategoriaEntity categoriaEntity = categoriaDAO.save(CategoriaEntity.converter(categoria));
        return categoriaEntity.toCategoria();
    }

    @Override
    public void deletarCategoria(Long id) {

        CategoriaEntity categoriaEntity = categoriaDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        categoriaDAO.deleteById(categoriaEntity.getId());

    }

    @Override
    public Categoria buscarCategoria(Long id) {

        Optional<CategoriaEntity> categoriaEntity = categoriaDAO.findById(id);

        return Categoria.builder()
                .id(categoriaEntity.get().getId())
                .nome(categoriaEntity.get().getNome())
                .status(categoriaEntity.get().getStatus())
                .produtos(categoriaEntity.get().toProdutos())
                .build();
    }

    @Override
    public Categoria atualizar(Long id, String nomeCategoria) {

        CategoriaEntity categoriaEntity = categoriaDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        categoriaEntity.setNome(nomeCategoria);

        return categoriaEntity.toCategoria();
    }

    @Override
    public Categoria atualizarStatus(Long id) {

        CategoriaEntity categoria = categoriaDAO.getReferenceById(id);

        if (categoria.getStatus().equals(StatusCategoria.ATIVA)) categoria.setStatus(StatusCategoria.INATIVA);

        categoria.setStatus(StatusCategoria.ATIVA);

        return categoria.toCategoria();
    }
}
