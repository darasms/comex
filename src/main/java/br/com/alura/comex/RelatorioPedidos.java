package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class RelatorioPedidos extends Relatorio{
    public RelatorioPedidos(List<Pedido> listaDePedidos) {
        super(listaDePedidos);
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
                .orElseThrow(() -> new IllegalStateException());
    }

    public Pedido getPedidoMaisCaro(){
        return listaDePedidos.stream()
                .max(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException());
    }
}
