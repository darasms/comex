package br.com.alura.comex.entity.produto;

import java.util.List;

public interface ProdutoRepository {

    List<Produto> listarTodosProdutosCadastrados();

    Produto buscarProdutoPorCodProduto(Long codigoProduto);

    Produto cadastrarProduto(Produto produto);

    Produto atualizarProduto(Long codigoProduto, Produto produto);

    void excluirProduto(Long codigoProduto);
}
