package br.com.alura.comex;


import java.io.FileNotFoundException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String caminho_arquivo = "src/main/resources/pedidos.csv";

        ProcessadorDeCsv arquivo = new ProcessadorDeCsv();
        List<Pedido> pedidos = arquivo.getPedidos(caminho_arquivo);

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
