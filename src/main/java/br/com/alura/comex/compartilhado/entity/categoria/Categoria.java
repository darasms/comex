package br.com.alura.comex.compartilhado.entity.categoria;

import br.com.alura.comex.compartilhado.entity.enuns.StatusCategoria;
import br.com.alura.comex.estoque.entity.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Categoria {

    private Long id;

    private String nome;

    private StatusCategoria status = StatusCategoria.ATIVA;
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produto.setCategoria(this);
        this.produtos.add(produto);
    }

}
