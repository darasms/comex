package br.com.alura.comex.pedido.usecase;


import br.com.alura.comex.pedido.entity.enuns.TipoDesconto;
import br.com.alura.comex.pedido.entity.enuns.TipoDescontoItem;
import br.com.alura.comex.pedido.entity.pedido.Pedido;

import java.math.BigDecimal;

public class Descontos {

    public static void aplicarDesconto(Pedido pedido){
        if (pedido.getQuantidadeDeProdutos() > 10){
            BigDecimal valorDesconto = pedido.getValorTotalPedido().multiply(new BigDecimal("0.1"));
            pedido.getItens().stream().forEach(i -> {
                i.setDesconto(valorDesconto);
                i.setTipoDesconto(TipoDescontoItem.QUANTIDADE);
            });
        }
        if (pedido.getCliente().quantidadePedidos() > 5){
            BigDecimal valorDesconto = pedido.getValorTotalPedido().multiply(new BigDecimal("0.05"));
            pedido.setDesconto(valorDesconto);
            pedido.setTipoDesconto(TipoDesconto.FIDELIDADE);
        }

    }

}
