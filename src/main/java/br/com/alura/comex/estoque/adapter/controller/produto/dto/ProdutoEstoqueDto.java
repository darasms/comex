package br.com.alura.comex.estoque.adapter.controller.produto.dto;

import br.com.alura.comex.compartilhado.adapter.controller.categoria.dto.CategoriaDto;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoque;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class ProdutoEstoqueDto {


    private Long codigoProduto;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private CategoriaDto categoria;

    public ProdutoEstoqueDto(ProdutoEstoque produtoEstoqueEntity) {
        this.codigoProduto = produtoEstoqueEntity.getCodigoProduto();
        this.nome = produtoEstoqueEntity.getNome();
        this.descricao = produtoEstoqueEntity.getDescricao();
        this.precoUnitario = produtoEstoqueEntity.getPrecoUnitario();
        this.quantidadeEstoque = produtoEstoqueEntity.getQuantidadeEstoque();
        this.categoria = new CategoriaDto(produtoEstoqueEntity.getCategoria());
    }


    public static List<ProdutoEstoqueDto> converter(Page<ProdutoEstoque> produtos) {
        return produtos.stream().map(ProdutoEstoqueDto::new).toList();
    }
    public static Page<ProdutoEstoqueDto> converterPagina(Page<ProdutoEstoque> produtos) {
        return produtos.map(ProdutoEstoqueDto::new);
    }

}
