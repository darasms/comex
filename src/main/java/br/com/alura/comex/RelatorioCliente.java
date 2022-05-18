package br.com.alura.comex;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioCliente extends Relatorio{

    public RelatorioCliente(List<Pedido> listaDePedidos) {
        super(listaDePedidos);
    }

    public Map<String, List<Pedido>> getPedidosPorCliente() {

        Map<String, List<Pedido>>pedidosPorCliente = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente)).forEach((cli, prods) -> pedidosPorCliente.put(cli, prods));
        return pedidosPorCliente;
    }

    public void printClientesFieis(){
        System.out.println("\n#### RELATÓRIO DE CLIENTES FIÉIS");
        this.getPedidosPorCliente().forEach((cliente, produto) -> System.out.printf("\nNOME: %s \nNº DE PEDIDOS: %s\n", cliente, produto.size()));
    }

}
