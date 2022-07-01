package br.com.alura.comex.entity.produto;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository {

    List<Produto> listarTodosProdutosCadastrados();

    Produto buscarProdutoPorCodProduto(Long codigoProduto);

    void cadastrarProduto(Produto produto);

    Produto atualizarPrecoProduto(Long codigoProduto, BigDecimal novoPrecoUnitario);

    Produto atualizarQuantidadeEmEstoque(Long codigoProduto, int novaQuantidade);


    void excluirProduto(Long codigoProduto);
}
