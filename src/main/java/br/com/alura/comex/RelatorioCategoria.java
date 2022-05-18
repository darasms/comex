package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class RelatorioCategoria extends Relatorio{

    public RelatorioCategoria(List<Pedido> listaDePedidos) {
        super(listaDePedidos);
    }

    public int getTotalDeCategorias(){
        HashSet<String> categoriasProcessadas = new HashSet<>();

        this.listaDePedidos.stream()
                .map(Pedido::getCategoria)
                .forEach(c -> categoriasProcessadas.add(c));

        return categoriasProcessadas.size();
    }

    public Map<String, Integer> getQtdProdutosPorCategoria(){

        Map<String, Integer>  qtdProdutosPorCategoria = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCategoria))
                .forEach((a,v) -> qtdProdutosPorCategoria.put(a,v.stream().mapToInt(Pedido::getQuantidade).sum()));
        return qtdProdutosPorCategoria;

    }

    public  Map<String, BigDecimal> getMontantePorCategoria(){

        Map<String, BigDecimal> montantePorCategoria = new TreeMap<>();
        listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCategoria))
                .forEach((a,v) -> montantePorCategoria.put(a, v.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add)));
        return montantePorCategoria;

    }

    public Map<String, String> produtoMaisCaroCategoria() throws IllegalArgumentException{
        Map<String, String> produtoMaisCaroCategoria = new TreeMap<>();

        listaDePedidos.stream().collect(Collectors.groupingBy(Pedido::getCategoria)).forEach((a, b) -> {
            produtoMaisCaroCategoria.put(a, b.stream().max(Comparator.comparing(Pedido::getPreco))
                    .orElseThrow(() -> new IllegalStateException("Não foi possível encontrar o produto mais caro da Categoria: " + a)).getProduto());
        });
        return produtoMaisCaroCategoria;
    }

    public Map<String, BigDecimal> maiorPrecoPorCategoria(){
        Map<String, BigDecimal> maiorPrecoPorCategoria = new TreeMap<>();
        listaDePedidos.stream().collect(Collectors.groupingBy(Pedido::getCategoria)).forEach((a, b) -> {
            maiorPrecoPorCategoria.put(a, b.stream().max(Comparator.comparing(Pedido::getPreco))
                    .orElseThrow(() -> new IllegalStateException("Não foi possível encontrar o maior valor Categoria: " + a)).getPreco());
        });
        return maiorPrecoPorCategoria;
    }

}
