package br.com.alura.comex;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioClienteFieis extends Relatorio{

    public RelatorioClienteFieis(List<Pedido> listaDePedidos) {
        super(listaDePedidos);
    }

    public Map<String, Long> getPedidosPorCliente() {

        Map<String, Long> pedidosPorCliente = listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()));

        return pedidosPorCliente;

    }

    public void printClientesFieis(){
        System.out.println("\n#### RELATÓRIO DE CLIENTES FIÉIS");
        this.getPedidosPorCliente().forEach((cliente, c) -> System.out.printf("\nNOME: %s \nNº DE PEDIDOS: %s\n", cliente, c));
    }

}
