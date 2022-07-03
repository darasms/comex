package br.com.alura.comex.adapter.form;

import br.com.alura.comex.infra.categoria.CategoriaEntity;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import br.com.alura.comex.infra.categoria.CategoriaDAO;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    private String descricao;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    @NotNull
    private Long categoria;

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

    public Long getCategoria() {
        return categoria;
    }

    public ProdutoEntity converter(CategoriaDAO categoriaRepository) {
        Optional<CategoriaEntity> novaCategoria = categoriaRepository.findById(categoria);
        return ProdutoEntity.builder()
                .nome(nome)
                .descricao(descricao)
                .precoUnitario(precoUnitario)
                .quantidadeEstoque(quantidadeEstoque)
                .categoria(novaCategoria.get())
                .build();
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
