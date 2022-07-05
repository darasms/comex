package br.com.alura.comex.estoque.entity.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

    public interface ProdutoRepository {

        List<Produto> listarTodosProdutosCadastrados();

        Produto buscarProdutoPorCodProduto(Long codigoProduto);

        Produto cadastrarProduto(Produto produto);

        Produto atualizarProduto(Long codigoProduto, Produto produto);

        void excluirProduto(Long codigoProduto);

        Page<Produto> listarProdutosCadastradosPaginados(Pageable pegeable);
}
