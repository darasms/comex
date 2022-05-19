package br.com.alura.comex;


import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        ProcessadorDeCsv arquivo = new ProcessadorDeCsv();
        List<Pedido> pedidos = arquivo.getPedidos("pedidos.csv");

        RelatorioClienteFieis relatorioClienteFieis = new RelatorioClienteFieis(pedidos);
        relatorioClienteFieis.printClientesFieis();

        RelatorioCategoria relatorioCategoria = new RelatorioCategoria(pedidos);
        relatorioCategoria.imprimeVendasPorCategoria();

        RelatórioProdutos relatorioProduto = new RelatórioProdutos(pedidos);
        relatorioProduto.imprimeProdutosMaisVendidos();

        relatorioCategoria.imprimeProdutoMaisCaroPorCategoria();

        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);
        relatorioSintetico.imprimeValoresTotais();


    }
}
