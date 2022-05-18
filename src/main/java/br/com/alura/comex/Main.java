package br.com.alura.comex;


import java.util.*;


public class Main {

    public static void main(String[] args) {

        ProcessadorDeCsv arquivo = new ProcessadorDeCsv();
        List<Pedido> pedidos = arquivo.getPedidos("pedidos.csv");

        GerarRelatorioPedidos relatorio = new GerarRelatorioPedidos(pedidos);

        relatorio.printValoresTotais();
        relatorio.printClientesFieis();
        relatorio.printVendasPorCategoria();
        relatorio.printProdutosMaisVendidos();
        relatorio.printProdutoMaisCaroPorCategoria();
    }
}
