package br.com.alura.comex;


import java.util.*;


public class Main {

    public static void main(String[] args) {

        ProcessadorDeCsv arquivo = new ProcessadorDeCsv();
        List<Pedido> pedidos = arquivo.getPedidos("pedidos.csv");

        GerarRelatorioPedidos relatorio = new GerarRelatorioPedidos(pedidos);

        relatorio.imprimeValoresTotais();
        relatorio.imprimeClientesFieis();
        relatorio.imprimeVendasPorCategoria();
        relatorio.imprimeProdutosMaisVendidos();
        relatorio.imprimeProdutoMaisCaroPorCategoria();
    }
}
