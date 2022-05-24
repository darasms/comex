package br.com.alura.comex;


import br.com.alura.comex.processadorarquivo.ProcessadorJSON;
import br.com.alura.comex.processadorarquivo.ProcessadorXML;

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        String xmlPedido = "src/main/resources/pedidos.xml";
        List<Pedido> pedidos = new ProcessadorXML().getPedidos(xmlPedido);


        RelatorioClientesLucrativos relatorioClientesLucrativos = new RelatorioClientesLucrativos(pedidos);
        relatorioClientesLucrativos.imprimeClientesLucrativos(2);

        RelatorioClienteFieis relatorioClienteFieis = new RelatorioClienteFieis(pedidos);
        relatorioClienteFieis.imprimeClientesFieis();

        RelatorioCategoria relatorioCategoria = new RelatorioCategoria(pedidos);
        relatorioCategoria.imprimeVendasPorCategoria();

        RelatórioProdutos relatorioProdutos = new RelatórioProdutos(pedidos);
        relatorioProdutos.imprimeProdutosMaisVendidos();

        relatorioCategoria.imprimeProdutoMaisCaroPorCategoria();

        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);
        relatorioSintetico.imprimeValoresTotais();


    }
}
