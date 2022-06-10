package br.com.alura.comex.controller.form;

import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Optional;

public class AtualizacaoProdutoForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    private String descricao;
    @NotNull
    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String categoria;



    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {

        Optional<Produto> produto = produtoRepository.findById(id);
        produto.get().setName(this.nome);
        produto.get().setDescricao(this.descricao);
        produto.get().setPrecoUnitario(this.precoUnitario);
        produto.get().setQuantidadeEstoque(this.quantidadeEstoque);
        return produto.get();

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
