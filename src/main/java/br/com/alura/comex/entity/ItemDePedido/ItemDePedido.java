package br.com.alura.comex.entity.ItemDePedido;

import br.com.alura.comex.entity.Pedido.Pedido;
import br.com.alura.comex.entity.Produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDePedido {

    private BigDecimal precoUnitario;
    private Integer quantidade;

    private Pedido pedido;

    private Produto produto;

    private BigDecimal desconto = BigDecimal.ZERO;

    private TipoDescontoItem tipoDesconto = TipoDescontoItem.NENHUM;

    public BigDecimal getValorTotalItem() {
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }

}
