package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelatorioProdutosMaisVendidosTest {

    private GeradorRelatorio relatorio;
    private List<Pedido> listaPedidos;

    @BeforeEach
    public void inicio() {
        relatorio = new RelatorioProdutosMaisVendidos();
        listaPedidos = new ArrayList<>();
    }

    @Test
    @DisplayName("Teste Relatório Produtos Mais Vendidos - sem lista de pedidos")
    public void relatorioSemListaDePedidos() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            relatorio.gerarConteudo(new ArrayList<>());
        });

    }

    @Test
    @DisplayName("Teste Relatório Produtos Mais Vendidos - lista com um pedido")
    public void relatorioDeUmUnicoPedido() {

        listaPedidos.add(geraListaComUmPedido());

        assertEquals(relatorio.gerarConteudo(listaPedidos), this.conteudoEsperadoUmUnicoPedido());
    }

    @Test
    @DisplayName("Teste Relatório Produtos Mais Vendidos - lista com produtos com a mesma quantidade de vendas")
    public void relatorioComProdutosDeMesmaQuantidade() {
        assertEquals(relatorio.gerarConteudo(geraListaPedidosComMaisDeUmaCategoriaEQuantidadesIguais()),
                this.conteudoEsperadoParaQuantidadesIguaisDeProduto());
    }

    @Test
    @DisplayName("Teste Cabeçalho")
    public void cabecalhoRelatorioProdutosMaisVendidos(){
        assertEquals("\n#### RELATÓRIO DE PRODUTOS MAIS VENDIDOS", relatorio.gerarCabecalho());

    }

    private List<Pedido> geraListaPedidosComMaisDeUmaCategoriaEQuantidadesIguais() {
        int year = 2022;
        int month = 4;
        int day = 29;

        List<Pedido> listaDePedidos = new ArrayList<>();

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("Impressora").
                preco(new BigDecimal(300)).
                quantidade(6).
                data(LocalDate.of(year, month, day)).
                cliente("Bela").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("Notebook Samsung").
                preco(new BigDecimal(100)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("CAIO").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("AUTOMOTIVA").
                produto("Central multimidia").
                preco(new BigDecimal("711.18")).
                quantidade(6).
                data(LocalDate.of(year, month, day)).
                cliente("Bela").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("LIVROS").
                produto("Building Microservices").
                preco(new BigDecimal("300.28")).
                quantidade(2).
                data(LocalDate.of(year, month, day)).
                cliente("CAIO").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("LIVROS").
                produto("Building Microservices").
                preco(new BigDecimal("300.28")).
                quantidade(2).
                data(LocalDate.of(year, month, day)).
                cliente("CAIO").
                build());

        return listaDePedidos;
    }

    private String conteudoEsperadoParaQuantidadesIguaisDeProduto(){
        return """
                \nPRODUTO: Central multimidia
                QUANTIDADE: 6
                \nPRODUTO: Impressora
                QUANTIDADE: 6
                \nPRODUTO: Building Microservices
                QUANTIDADE: 4
                """;

    }

    private String conteudoEsperadoUmUnicoPedido() {
        return """
                \nPRODUTO: IMPRESSORA
                QUANTIDADE: 1
                """;
    }

    private Pedido geraListaComUmPedido() {
        int year = 2022;
        int month = 4;
        int day = 29;

        return new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(300)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Bela").
                build();
    }

}