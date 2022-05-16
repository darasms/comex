package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class RelatorioSintetico {

    int totalDeProdutosVendidos;
    int totalDePedidosRealizados;
    BigDecimal montanteDeVendas = BigDecimal.ZERO;
    Pedido pedidoMaisBarato;
    Pedido pedidoMaisCaro;

    public RelatorioSintetico(List<Pedido> listaDePedidos){

        this.pedidoMaisBarato = listaDePedidos.stream()
                .min(Comparator.comparing(Pedido::getPreco))
                .get();

        this.pedidoMaisCaro = listaDePedidos.stream()
                .max(Comparator.comparing(Pedido::getPreco))
                .get();

        //montanteDeVendas = montanteDeVendas.add(pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())));

        Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();

        this.montanteDeVendas = listaDePedidos.stream()
                .map(mapaPedidos)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalDeProdutosVendidos = listaDePedidos.stream()
                .mapToInt(Pedido::getQuantidade)
                .sum();

        this.totalDePedidosRealizados = listaDePedidos.size();

    }

    public int getTotalDeProdutosVendidos() {
        return totalDeProdutosVendidos;
    }

    public int getTotalDePedidosRealizados() {
        return totalDePedidosRealizados;
    }

    public BigDecimal getMontanteDeVendas() {
        return montanteDeVendas;
    }

    public Pedido getPedidoMaisBarato() {
        return pedidoMaisBarato;
    }

    public Pedido getPedidoMaisCaro() {
        return pedidoMaisCaro;
    }
}
