package br.com.alura.comex.adapter.dto.pedido;

import br.com.alura.comex.entity.itemDePedido.ItemDePedido;
import br.com.alura.comex.infra.ItemDePedido.ItemDePedidoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItemDePedidoDto {
    private Long id;
    
    private BigDecimal precoUnitario;
    
    private Integer quantidade;
    
    private String produto;

    private String categoria;

    private BigDecimal desconto;

    private BigDecimal valorFinal;

    public ItemDePedidoDto(ItemDePedido item) {
        this.id = item.getId();
        this.precoUnitario = item.getPrecoUnitario();
        this.quantidade = item.getQuantidade();
        this.produto = item.getProduto().getNome();
        this.categoria = item.getProduto().getCategoria().getNome();
        this.desconto = item.getDesconto();
        this.valorFinal = item.getValorTotalItem();
    }

}
