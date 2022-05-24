package br.com.alura.comex;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioClienteFieis{

    private final List<Pedido> listaDePedidos;

    public RelatorioClienteFieis(List<Pedido> listaDePedidos) {

        if (listaDePedidos.isEmpty()) throw new IllegalArgumentException("Lista de pedidos nula ou vazia!");
        this.listaDePedidos = listaDePedidos;
    }

    public Map<String, Long> getPedidosPorCliente() {

        return listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()));
    }

    public void imprimeClientesFieis() {
        System.out.println("\n#### RELATÓRIO DE CLIENTES FIÉIS");
        this.getPedidosPorCliente().forEach((cliente, c) -> System.out.printf("\nNOME: %s \nNº DE PEDIDOS: %s\n", cliente, c));
    }

}
