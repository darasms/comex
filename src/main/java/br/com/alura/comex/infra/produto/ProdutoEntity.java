package br.com.alura.comex.infra.produto;

import br.com.alura.comex.entity.produto.Produto;
import br.com.alura.comex.infra.categoria.CategoriaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoProduto;
    @Column(nullable = false)
    private String nome;
    private String descricao;
    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;
    @Column(name = "quantidade_estoque", nullable = false)
    @Min(0)
    private int quantidadeEstoque;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriaEntity categoria;


    public static ProdutoEntity converter(Produto produto) {

        return ProdutoEntity.builder()
                .nome(produto.getNome())
                .codigoProduto(produto.getCodigoProduto())
                .descricao(produto.getDescricao())
                .precoUnitario(produto.getPrecoUnitario())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .categoria(CategoriaEntity.converter(produto.getCategoria()))
                .build();
    }

    public Produto paraProduto() {

        return Produto.builder()
                .codigoProduto(this.codigoProduto)
                .nome(this.nome)
                .descricao(this.descricao)
                .precoUnitario(this.precoUnitario)
                .quantidadeEstoque(this.quantidadeEstoque)
                .categoria(this.categoria.toCategoria())
                .build();
    }
}
