package br.com.alura.comex.estoque.adapter.controller.produto.form;

import br.com.alura.comex.compartilhado.entity.categoria.Categoria;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaRepositoryImpl;
import br.com.alura.comex.estoque.entity.produto.ProdutoEstoque;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter @Setter
@ToString
public class ProdutoEstoqueForm {

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


//    public ProdutoEntity converter(CategoriaDAO categoriaRepository) {
//        Optional<CategoriaEntity> novaCategoria = categoriaRepository.findById(categoria);
//        return ProdutoEntity.builder()
//                .nome(nome)
//                .descricao(descricao)
//                .precoUnitario(precoUnitario)
//                .quantidadeEstoque(quantidadeEstoque)
//                .categoria(novaCategoria.get())
//                .build();
//    }
    public ProdutoEstoque converterEmProduto(CategoriaRepositoryImpl categoriaRepository) {
        Categoria novaCategoria = categoriaRepository.buscarCategoria(categoria);
        return ProdutoEstoque.builder()
                .nome(nome)
                .descricao(descricao)
                .precoUnitario(precoUnitario)
                .quantidadeEstoque(quantidadeEstoque)
                .categoria(novaCategoria)
                .build();
    }

}
