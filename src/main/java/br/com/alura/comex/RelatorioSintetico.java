package br.com.alura.comex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RelatorioSintetico {
    private final List<Pedido> listaDePedidos;

    public RelatorioSintetico(List<Pedido> listaDePedidos){

        if (listaDePedidos == null || listaDePedidos.isEmpty()) throw new IllegalArgumentException("Lista de pedidos nula ou vazia!");

        this.listaDePedidos = listaDePedidos;
    }

    public void imprimeValoresTotais(){

        RelatorioPedidos relatorioPedidos = new RelatorioPedidos(listaDePedidos);
        RelatórioProdutos relatorioProduto = new RelatórioProdutos(listaDePedidos);
        RelatorioCategoria relatorioCategoria = new RelatorioCategoria(listaDePedidos);

        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioPedidos.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioProduto.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioCategoria.getTotalDeCategorias());
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioPedidos.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioPedidos.getPedidoMaisBarato().getValorTotal().setScale(2, RoundingMode.HALF_DOWN)), relatorioPedidos.getPedidoMaisBarato().getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioPedidos.getPedidoMaisCaro().getValorTotal().setScale(2, RoundingMode.HALF_DOWN)), relatorioPedidos.getPedidoMaisCaro().getProduto());
    }

}
