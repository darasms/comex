package br.com.alura.comex.loja.adapter.controller.produto.form;

import br.com.alura.comex.compartilhado.entity.categoria.Categoria;
import br.com.alura.comex.compartilhado.entity.produto.Dimensao;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaRepositoryImpl;
import br.com.alura.comex.loja.entity.produto.ProdutoLoja;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProdutoLojaForm {

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

    private int comprimento;

    private int altura;

    private int largura;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal peso;

    public ProdutoLoja converterEmProdutoLoja(CategoriaRepositoryImpl categoriaRepository) {
        Categoria novaCategoria = categoriaRepository.buscarCategoria(categoria);
        return ProdutoLoja.builder()
                .nome(nome)
                .descricao(descricao)
                .precoUnitario(precoUnitario)
                .quantidadeEstoque(quantidadeEstoque)
                .categoria(novaCategoria)
                .dimensao(Dimensao.builder()
                        .comprimento(this.comprimento)
                        .altura(this.altura)
                        .largura(this.largura)
                        .peso(this.peso)
                        .build())
                .build();
    }


}
