package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioClientesLucrativos {
    private final List<Pedido> listaDePedidos;

    public RelatorioClientesLucrativos(List<Pedido> listaDePedidos) {
        if (listaDePedidos.isEmpty()) throw new IllegalArgumentException("Lista de pedidos nula ou vazia!");
        this.listaDePedidos = listaDePedidos;
    }

    private Map<String, BigDecimal> getClientesLucrativos() {

        Map<String, BigDecimal>  clientesLucrativos = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente))
                .forEach((a,v) -> {
                    clientesLucrativos.put(a, v.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
                });

        return clientesLucrativos;
    }

    private Map<String, Long> quantidadePedidoClienteMaisLucrativo(){

        return listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()));
    }

    public void imprimeClientesLucrativos(Integer quantidadeCliente){

        System.out.println("\n#### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS");
        this.getClientesLucrativos()
                .entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(quantidadeCliente)
                .forEach(a -> {
                    System.out.printf("\nCLIENTE: %s\nNº PEDIDOS: %s", a.getKey(), quantidadePedidoClienteMaisLucrativo().get(a.getKey()));
                    System.out.printf("\nMONTANTE GASTO: %s\n", FormatosImpressao.getRealFormat(a.getValue()));
                });

    }
}
