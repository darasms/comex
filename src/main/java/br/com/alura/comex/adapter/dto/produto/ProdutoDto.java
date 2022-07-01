package br.com.alura.comex.adapter.dto.produto;

import br.com.alura.comex.adapter.dto.categorias.CategoriaDto;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoDto {


    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private CategoriaDto categoria;

    public ProdutoDto(ProdutoEntity produtoEntity) {
        this.id = produtoEntity.getId();
        this.nome = produtoEntity.getNome();
        this.descricao = produtoEntity.getDescricao();
        this.precoUnitario = produtoEntity.getPrecoUnitario();
        this.quantidadeEstoque = produtoEntity.getQuantidadeEstoque();
        this.categoria = new CategoriaDto(produtoEntity.getCategoria());
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public static List<ProdutoDto> converter(Page<ProdutoEntity> produtos) {
        return produtos.stream().map(ProdutoDto::new).toList();
    }
    public static Page<ProdutoDto> converterPagina(Page<ProdutoEntity> produtos) {
        return produtos.map(ProdutoDto::new);
    }

}
