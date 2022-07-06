package br.com.alura.comex.estoque.adapter.controller.produto.dto;

import br.com.alura.comex.estoque.infra.produto.ProdutoEstoqueEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalhesDeProdutoEstoqueDto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private String categoria;

    public DetalhesDeProdutoEstoqueDto(ProdutoEstoqueEntity produtoEstoqueEntity) {
        this.id = produtoEstoqueEntity.getCodigoProduto();
        this.nome = produtoEstoqueEntity.getNome();
        this.descricao = produtoEstoqueEntity.getDescricao();
        this.precoUnitario = produtoEstoqueEntity.getPrecoUnitario();
        this.quantidadeEstoque = produtoEstoqueEntity.getQuantidadeEstoque();
        this.categoria = produtoEstoqueEntity.getCategoria().getNome();
    }

}
