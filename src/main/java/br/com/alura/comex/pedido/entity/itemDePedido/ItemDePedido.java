package br.com.alura.comex.pedido.entity.itemDePedido;

import br.com.alura.comex.pedido.entity.enuns.TipoDescontoItem;
import br.com.alura.comex.pedido.entity.pedido.Pedido;
import br.com.alura.comex.pedido.entity.produto.Produto;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter @Builder
public class ItemDePedido {

    private Long id;
    private BigDecimal precoUnitario;
    private Integer quantidade;

    private Pedido pedido;

    private Produto produto;

    private BigDecimal desconto = BigDecimal.ZERO;

    private TipoDescontoItem tipoDesconto = TipoDescontoItem.NENHUM;

    public ItemDePedido(int quantidadeProduto, Produto produto) {
        this.quantidade = quantidadeProduto;
        this.produto = produto;
        this.precoUnitario = produto.getPrecoUnitario();
    }

    public BigDecimal getValorTotalItem() {
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }

}
