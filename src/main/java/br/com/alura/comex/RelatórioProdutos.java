package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatórioProdutos extends Relatorio{

    public RelatórioProdutos(List<Pedido> listaDePedidos) {
        super(listaDePedidos);
    }

    public int getTotalDeProdutosVendidos(){
        return listaDePedidos.stream()
             .mapToInt(Pedido::getQuantidade)
             .sum();
    }

    public Map<String, Integer> getProdutosMaisVendidos(){
        Map<String, Integer>  produtosMaisVendidos = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getProduto))
                .forEach((a,v) -> produtosMaisVendidos.put(a,v.stream().mapToInt(Pedido::getQuantidade).sum()));
        return produtosMaisVendidos;
    }

}
