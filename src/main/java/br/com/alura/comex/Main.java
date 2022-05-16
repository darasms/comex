package br.com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ProcessadorDeCsv arq = new ProcessadorDeCsv();

        List<Pedido> pedidos = arq.getPedidos("pedidos.csv");

//        int totalDeProdutosVendidos = 0;
//        int totalDePedidosRealizados = 0;
//        BigDecimal montanteDeVendas = BigDecimal.ZERO;
//        Pedido pedidoMaisBarato = null;
//        Pedido pedidoMaisCaro = null;

        CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();
        int totalDeCategorias = 0;

        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);

//
//        for (int i = 0; i < pedidos.size(); i++) {
//            Pedido pedidoAtual = pedidos.get(i);
//
//            if (pedidoAtual == null) {
//                break;
//            }

//            if (pedidoMaisBarato == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade()))) < 0) {
//                pedidoMaisBarato = pedidoAtual;
//            }
//
//            if (pedidoMaisCaro == null || pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())).compareTo(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade()))) > 0) {
//                pedidoMaisCaro = pedidoAtual;
//            }

//            montanteDeVendas = montanteDeVendas.add(pedidoAtual.getPreco().multiply(new BigDecimal(pedidoAtual.getQuantidade())));
//            totalDeProdutosVendidos += pedidoAtual.getQuantidade();
//            totalDePedidosRealizados++;

//            if (!categoriasProcessadas.contains(pedidoAtual.getCategoria())) {
//              totalDeCategorias++;
//              categoriasProcessadas.add(pedidoAtual.getCategoria());
//            }
//        }

//        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
//        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", totalDePedidosRealizados);
//        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", totalDeProdutosVendidos);
//        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", totalDeCategorias);
//        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(montanteDeVendas.setScale(2, RoundingMode.HALF_DOWN)));
//        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidoMaisBarato.getPreco().multiply(new BigDecimal(pedidoMaisBarato.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisBarato.getProduto());
//        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedidoMaisCaro.getPreco().multiply(new BigDecimal(pedidoMaisCaro.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMaisCaro.getProduto())

        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioSintetico.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioSintetico.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", totalDeCategorias);
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        System.out.println("- PEDIDO MAIS BARATO: " + relatorioSintetico.getPedidoMaisBarato().getValorTotal());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getPedidoMaisCaro().getValorTotal().setScale(2, RoundingMode.HALF_DOWN)));
    }
}
