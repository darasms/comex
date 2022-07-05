package br.com.alura.comex.pedido.entity.categoria;

import java.math.BigDecimal;

public interface RelatorioPedidosPorCategoriaProjecao {
    String getNome();
    Long getQuantidadeProdutos();
    BigDecimal getMontanteVendido();
}
