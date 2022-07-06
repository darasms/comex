package br.com.alura.comex.estoque.adapter.controller.produto.dto;

import br.com.alura.comex.estoque.infra.produto.ProdutoEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalhesDeProdutoDto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private String categoria;

    public DetalhesDeProdutoDto(ProdutoEntity produtoEntity) {
        this.id = produtoEntity.getCodigoProduto();
        this.nome = produtoEntity.getNome();
        this.descricao = produtoEntity.getDescricao();
        this.precoUnitario = produtoEntity.getPrecoUnitario();
        this.quantidadeEstoque = produtoEntity.getQuantidadeEstoque();
        this.categoria = produtoEntity.getCategoria().getNome();
    }

}
