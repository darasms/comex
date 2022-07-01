package br.com.alura.comex.entity.Categoria;

import br.com.alura.comex.entity.Produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Categoria {

    private String nome;

    private StatusCategoria status = StatusCategoria.ATIVA;
    private List<Produto> produtos;

    public void adicionarProduto(String nome,  String descricao, BigDecimal precoUnitario,
                                 int quantidadeEstoque, Categoria categoria) {
        this.produtos.add(Produto.builder()
                        .nome(nome)
                        .descricao(descricao)
                        .precoUnitario(precoUnitario)
                        .quantidadeEstoque(quantidadeEstoque)
                        .categoria(categoria)
                        .build());
    }

}
