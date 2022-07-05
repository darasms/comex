package br.com.alura.comex.estoque.entity.produto;

import br.com.alura.comex.compartilhado.entity.categoria.Categoria;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class Produto {

    private Long codigoProduto;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private Categoria categoria;

}
