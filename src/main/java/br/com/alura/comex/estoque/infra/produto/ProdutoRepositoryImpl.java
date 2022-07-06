package br.com.alura.comex.estoque.infra.produto;

import br.com.alura.comex.estoque.entity.produto.Produto;
import br.com.alura.comex.estoque.entity.produto.ProdutoRepository;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoDAO produtoDAO;

    @Override
    public Page<Produto> listarProdutosCadastradosPaginados(Pageable pegeable) {
        return produtoDAO.findAll(pegeable).map(ProdutoEntity::paraProduto);
    }

    @Override
    public List<Produto> listarTodosProdutosCadastrados() {
        List<ProdutoEntity> produtoEntities = produtoDAO.findAll();
        return produtoEntities.stream().map(ProdutoEntity::paraProduto).toList();
    }

    @Override
    public Produto buscarProdutoPorCodProduto(Long codigoProduto) {

        ProdutoEntity produtoEntity = produtoDAO.findById(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return produtoEntity.paraProduto();
    }

    @Override
    public Produto cadastrarProduto(Produto produto) {
        ProdutoEntity produtoEntity = produtoDAO.save(ProdutoEntity.converter(produto));
        return produtoEntity.paraProduto();
    }

    @Override
    public Produto atualizarProduto(Long codigoProduto, Produto produto) {

        ProdutoEntity produtoEntity = produtoDAO.findById(codigoProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        produtoEntity.setNome(produto.getNome());
        produtoEntity.setDescricao(produto.getDescricao());
        produtoEntity.setPrecoUnitario(produto.getPrecoUnitario());
        produtoEntity.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        produtoEntity.setCategoria(CategoriaEntity.converter(produto.getCategoria()));

        return produtoEntity.paraProduto();
    }

    @Override
    public void excluirProduto(Long codigoProduto) {

        ProdutoEntity produto = produtoDAO.findById(codigoProduto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        produtoDAO.deleteById(produto.getCodigoProduto());
    }
}
