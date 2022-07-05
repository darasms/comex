package br.com.alura.comex.adapter.form;

import br.com.alura.comex.entity.itemDePedido.ItemDePedido;
import br.com.alura.comex.entity.produto.Produto;
import br.com.alura.comex.infra.produto.ProdutoRepositoryImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
public class ItemDePedidoForm {

    @NotNull
    @Min(0)
    @Valid
    private Long idProduto;

    @Min(1)
    private int quantidadeProduto;

    private Produto verificarProduto(ProdutoRepositoryImpl produtoRepository){
        Produto produto = produtoRepository.buscarProdutoPorCodProduto(this.idProduto);

        if (produto.getQuantidadeEstoque() < this.quantidadeProduto){
            throw new RuntimeException("Sem produto em estoque");
        }

        produto.setQuantidadeEstoque(
                produto.getQuantidadeEstoque() - this.quantidadeProduto
        );

        return produto;
    }

    public ItemDePedido converter(ProdutoRepositoryImpl produtoRepository){
        return new ItemDePedido(this.quantidadeProduto, verificarProduto(produtoRepository));
    }

}
