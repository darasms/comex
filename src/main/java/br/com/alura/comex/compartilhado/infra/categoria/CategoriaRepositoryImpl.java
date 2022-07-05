package br.com.alura.comex.compartilhado.infra.categoria;

import br.com.alura.comex.compartilhado.entity.categoria.Categoria;
import br.com.alura.comex.compartilhado.entity.categoria.CategoriaRepository;
import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final CategoriaDAO categoriaDAO;

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

        CategoriaEntity categoriaEntity = categoriaDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return Categoria.builder()
                .id(categoriaEntity.getId())
                .nome(categoriaEntity.getNome())
                .status(categoriaEntity.getStatus())
                .produtos(categoriaEntity.toProdutos())
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

        if (categoria.getStatus().equals(StatusCategoria.ATIVA)) {
            categoria.setStatus(StatusCategoria.INATIVA);
        }else {
            categoria.setStatus(StatusCategoria.ATIVA);
        }
        return categoria.toCategoria();
    }
}
