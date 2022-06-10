package br.com.alura.comex.controller.form;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.repository.CategoriaRepository;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProdutoForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    private String descricao;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String categoria;

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

    public String getCategoria() {
        return categoria;
    }

    public Produto converter(CategoriaRepository categoriaRepository) {
        Categoria novaCategoria = categoriaRepository.findByNome(categoria);
        return new Produto(nome, descricao, precoUnitario, quantidadeEstoque, novaCategoria);
    }

    @Override
    public String toString() {
        return "ProdutoForm{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
