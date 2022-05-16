package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public class RelatorioSintetico {

    int totalDeProdutosVendidos;
    int totalDePedidosRealizados;
    int totalDeCategorias;
    BigDecimal montanteDeVendas;
    Pedido pedidoMaisBarato;
    Pedido pedidoMaisCaro;


    public RelatorioSintetico(List<Pedido> listaDePedidos){

        this.pedidoMaisBarato = listaDePedidos.stream()
                .min(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException());
                //.get();

        this.pedidoMaisCaro = listaDePedidos.stream()
                .max(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException());

        this.montanteDeVendas = listaDePedidos.stream()
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalDeProdutosVendidos = listaDePedidos.stream()
                .mapToInt(Pedido::getQuantidade)
                .sum();

        this.totalDePedidosRealizados = listaDePedidos.size();

        HashSet<String> categoriasProcessadas = new HashSet<>();

        listaDePedidos.stream()
                .map(Pedido::getCategoria)
                .forEach(c -> categoriasProcessadas.add(c));

        this.totalDeCategorias = categoriasProcessadas.size();
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

    public int getTotalDeCategorias() {
        return totalDeCategorias;
    }
}
