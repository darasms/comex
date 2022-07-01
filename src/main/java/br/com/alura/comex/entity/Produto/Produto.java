package br.com.alura.comex.entity.Produto;

import br.com.alura.comex.entity.Categoria.Categoria;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Produto {

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private Categoria categoria;

}
