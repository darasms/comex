package br.com.alura.comex;

import java.util.List;

public class RelatorioSintetico {
    private final List<Pedido> listaDePedidos;

    public RelatorioSintetico(List<Pedido> listaDePedidos) {

        if (listaDePedidos.isEmpty()) throw new IllegalArgumentException("Lista de pedidos nula ou vazia!");

        this.listaDePedidos = listaDePedidos;
    }

    public void imprimeValoresTotais() {

        RelatorioPedidos relatorioPedidos = new RelatorioPedidos(listaDePedidos);
        RelatórioProdutos relatorioProduto = new RelatórioProdutos(listaDePedidos);
        RelatorioCategoria relatorioCategoria = new RelatorioCategoria(listaDePedidos);

        System.out.println("\n#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioPedidos.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioProduto.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioCategoria.getTotalDeCategorias());
        System.out.printf("- MONTANTE DE VENDAS: %s\n", FormatosImpressao.getRealFormat(relatorioPedidos.getMontanteDeVendas()));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", FormatosImpressao.getRealFormat(relatorioPedidos.getPedidoMaisBarato().getValorTotal()), relatorioPedidos.getPedidoMaisBarato().getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", FormatosImpressao.getRealFormat(relatorioPedidos.getPedidoMaisCaro().getValorTotal()), relatorioPedidos.getPedidoMaisCaro().getProduto());
    }

}
