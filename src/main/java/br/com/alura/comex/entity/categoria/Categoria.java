package br.com.alura.comex.entity.categoria;

import br.com.alura.comex.entity.produto.Produto;
import br.com.alura.comex.infra.enuns.StatusCategoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Categoria {

    private Long id;

    private String nome;

    private StatusCategoria status = StatusCategoria.ATIVA;
    private List<Produto> produtos;

    public void adicionarProduto(Produto produto) {
        produto.setCategoria(this);
        this.produtos.add(produto);
    }

}
