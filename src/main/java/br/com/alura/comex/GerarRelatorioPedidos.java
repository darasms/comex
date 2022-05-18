package br.com.alura.comex;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GerarRelatorioPedidos {

    private final List<Pedido> listaDePedidos;
    private final RelatorioSintetico relatorioSintetico;

    public GerarRelatorioPedidos(List<Pedido> listaDePedidos) {
        if (listaDePedidos == null || listaDePedidos.isEmpty()) throw new IllegalArgumentException("Lista de pedidos nula ou vazia!");

        this.listaDePedidos = listaDePedidos;
        this.relatorioSintetico = new RelatorioSintetico(listaDePedidos);
    }

    public void imprimeValoresTotais(){

        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioSintetico.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioSintetico.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioSintetico.getTotalDeCategorias());
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getPedidoMaisBarato().getValorTotal().setScale(2, RoundingMode.HALF_DOWN)), relatorioSintetico.getPedidoMaisBarato().getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getPedidoMaisCaro().getValorTotal().setScale(2, RoundingMode.HALF_DOWN)), relatorioSintetico.getPedidoMaisCaro().getProduto());

    }

    public void imprimeClientesFieis(){

        System.out.println("\n#### RELATÓRIO DE CLIENTES FIÉIS");
        relatorioSintetico.getPedidosPorCliente().forEach((cliente, produto) -> System.out.printf("\nNOME: %s \nNº DE PEDIDOS: %s\n", cliente, produto.size()));
    }

    public void imprimeVendasPorCategoria(){

        System.out.println("\n#### RELATÓRIO DE VENDAS POR CATEGORIA");
        relatorioSintetico.getQtdProdutosPorCategoria().forEach((catg, qtd) -> {
            System.out.printf("\nCATEGORIA: %s\nQUANTIDADE VENDIDA: %s", catg, qtd);
            System.out.printf("\nMONTANTE: %s\n",relatorioSintetico.getMontantePorCategoria().get(catg));
        });
    }

    public void imprimeProdutosMaisVendidos(){

        System.out.println("\n#### RELATÓRIO DE PRODUTOS MAIS VENDIDOS");
        relatorioSintetico.getProdutosMaisVendidos()
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(a -> System.out.printf("\nPRODUTO: %s\nQUANTIDADE: %s\n", a.getKey(), a.getValue()));
    }

    public void imprimeProdutoMaisCaroPorCategoria(){

        System.out.println("\n#### RELATÓRIO DE PRODUTOS MAIS CAROS DE CADA CATEGORIA");
        relatorioSintetico.getProdutoMaisCaroCategoria().forEach((a, b) -> {
            System.out.printf("\nCATEGORIA: %s\nPRODUTO: %s", a, b);
            System.out.printf("\nPREÇO: %s\n", relatorioSintetico.getMaiorPrecoPorCategoria().get(a));
        });
    }
}
