package br.com.alura.comex.estoque.adapter.controller.produto.form;

import br.com.alura.comex.compartilhado.entity.categoria.Categoria;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaRepositoryImpl;
import br.com.alura.comex.estoque.entity.produto.Produto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter @Setter
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
    private Long categoria;


    public Produto paraProduto(CategoriaRepositoryImpl repository){

        Categoria categoria = repository.buscarCategoria(this.categoria);

        return Produto.builder()
                .nome(this.nome)
                .descricao(this.descricao)
                .precoUnitario(this.precoUnitario)
                .quantidadeEstoque(this.quantidadeEstoque)
                .categoria(categoria)
                .build();
    }
}
