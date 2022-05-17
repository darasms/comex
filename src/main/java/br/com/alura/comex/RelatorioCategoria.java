package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioCategoria {

    private final List<Pedido> listaDePedidos;
    private final String categoria;

    private Map<String, BigDecimal> montantePorCategoria;

    public RelatorioCategoria(List<Pedido> listaDePedidos, String categoria){
        this.listaDePedidos = listaDePedidos;
        this.categoria = categoria;
    }

    public Map<String, Integer> getQtdProdutosPorCategoria(){

        Map<String, Integer> qtdProdutosPorCategoria = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCategoria))
                .forEach((a,v) -> qtdProdutosPorCategoria.put(a,v.stream().mapToInt(Pedido::getQuantidade).sum()));
        return qtdProdutosPorCategoria;

    }

//    public BigDecimal montantePorCategoria(){
//
//        final BigDecimal montante;
//        listaDePedidos.stream()
//                .collect(Collectors.groupingBy(Pedido::getCategoria))
//                .forEach((a, v) -> montante = v.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
//        return montante;
//    }

}
