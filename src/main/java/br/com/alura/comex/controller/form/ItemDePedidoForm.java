package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ItemDePedidoForm {

    @NotNull
    @Min(0)
    @Valid
    private Long idProduto;

    @Min(0)
    private int quantidadeProduto;


    private Produto verificarProduto(ProdutoRepository produtoRepository){
        Optional<Produto> produto = produtoRepository.findById(this.idProduto);

        if (produto.get().getQuantidadeEstoque() == 0){
            throw new RuntimeException("Sem produto em estoque");
        }
        return produto.get();
    }

    public ItemDePedido converter(ProdutoRepository produtoRepository){
        return new ItemDePedido(this.quantidadeProduto, verificarProduto(produtoRepository));
    }


    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }
}
