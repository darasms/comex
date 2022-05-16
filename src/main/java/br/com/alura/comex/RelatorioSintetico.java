package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RelatorioSintetico {

    int totalDeProdutosVendidos;
    int totalDePedidosRealizados;
    int totalDeCategorias;
    BigDecimal montanteDeVendas;
    Pedido pedidoMaisBarato;
    Pedido pedidoMaisCaro;


    public RelatorioSintetico(List<Pedido> listaDePedidos){

        if (listaDePedidos == null || listaDePedidos.isEmpty()) throw new IllegalArgumentException("Lista de pedidos nula ou vazia!");

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

        Map<String, Integer> clientes = new HashMap<>();

        List<String> listaClient = listaDePedidos.stream()
                .map(Pedido::getCliente)
                .collect(Collectors.toList());

        listaClient.stream()
                        .forEach(p -> clientes.put(p, Collections.frequency(listaClient, p)));


        System.out.println("#### RELATÓRIO DE CLIENTES FIÉIS");
        clientes.forEach((a,b) -> System.out.println("NOME: " + a + "\n" + "Nº DE PEDIDOS: " + b + "\n"));
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
