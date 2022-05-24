package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class RelatorioPedidos{
    private final List<Pedido> listaDePedidos;

    public RelatorioPedidos(List<Pedido> listaDePedidos) {
        if (listaDePedidos.isEmpty()) throw new IllegalArgumentException("Lista de pedidos nula ou vazia!");
        this.listaDePedidos = listaDePedidos;
    }

    public int getTotalDePedidosRealizados(){
        return listaDePedidos.size();
    }

    public BigDecimal getMontanteDeVendas(){
        return listaDePedidos.stream()
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Pedido getPedidoMaisBarato(){
        return listaDePedidos.stream()
                .min(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException("Sem valores para comparação"));
    }

    public Pedido getPedidoMaisCaro(){
        return listaDePedidos.stream()
                .max(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException("Sem valores para comparação"));
    }
}
