package br.com.alura.comex;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws IOException {

//        String caminho_arquivo = "src/main/resources/pedidos.csv";
//
//        ProcessadorDeCsv arquivo = new ProcessadorDeCsv();
//        List<Pedido> pedidos = arquivo.getPedidos(caminho_arquivo);

        String jsonPedidosArray = "src/main/resources/pedidos.json";

        List<Pedido> pedidos = new ProcessadorJSON().getPedidos(jsonPedidosArray);

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
