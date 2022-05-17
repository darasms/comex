package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RelatorioSintetico {

    private int totalDeProdutosVendidos;
    private int totalDePedidosRealizados;
    private int totalDeCategorias;
    private BigDecimal montanteDeVendas;
    private Pedido pedidoMaisBarato;
    private Pedido pedidoMaisCaro;
    private Map<String, List<Pedido>>pedidosPorCliente;

    private Map<String, Integer> qtdProdutosPorCategoria;
    private Map<String, BigDecimal> montantePorCategoria;


    public RelatorioSintetico(List<Pedido> listaDePedidos){

        if (listaDePedidos == null || listaDePedidos.isEmpty()) throw new IllegalArgumentException("Lista de pedidos nula ou vazia!");

        this.pedidoMaisBarato = listaDePedidos.stream()
                .min(Comparator.comparing(Pedido::getValorTotal))
                .orElseThrow(() -> new IllegalStateException());

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

        this.pedidosPorCliente = new TreeMap();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente)).forEach((cli, prods) -> pedidosPorCliente.put(cli, prods));


        this.qtdProdutosPorCategoria = new TreeMap<>();

        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCategoria))
                .forEach((a,v) -> qtdProdutosPorCategoria.put(a,v.stream().mapToInt(Pedido::getQuantidade).sum()));

        this.montantePorCategoria = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCategoria))
                .forEach((a,v) -> montantePorCategoria.put(a, v.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add)));

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

    public Map<String, List<Pedido>> getPedidosPorCliente() { return pedidosPorCliente; }

    public Map<String, Integer> getQtdProdutosPorCategoria() {
        return qtdProdutosPorCategoria;
    }

    public Map<String, BigDecimal> getMontantePorCategoria() {
        return montantePorCategoria;
    }
}
