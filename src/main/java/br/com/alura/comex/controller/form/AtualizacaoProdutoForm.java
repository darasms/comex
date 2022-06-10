package br.com.alura.comex.controller.form;

import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.repository.ProdutoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AtualizacaoProdutoForm {

    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String nome;

    private String descricao;
    @NotNull
    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    @NotNull
    @NotEmpty
    @Length(min = 2)
    private String categoria;



    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {

        Produto produto = produtoRepository.getReferenceById(id);

        produto.setName(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPrecoUnitario(this.precoUnitario);
        produto.setQuantidadeEstoque(this.quantidadeEstoque);
        return produto;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
