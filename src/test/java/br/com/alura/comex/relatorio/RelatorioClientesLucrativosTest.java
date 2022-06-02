package br.com.alura.comex.relatorio;


import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import br.com.alura.comex.utils.FormatosImpressao;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class RelatorioClientesLucrativosTest {

    private GeradorRelatorio relatorio;
    private List<Pedido> listaPedidos;
    @BeforeEach
    public void inicio() {
        listaPedidos = new ArrayList<>();
        relatorio = new RelatorioClientesLucrativos();
    }

    @Test
    @DisplayName("Teste Relatório Clientes mais lucrativos - sem lista de pedidos")
    public void relatorioSemListaDePedidos() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            relatorio.gerarConteudo(new ArrayList<>());
        });

    }

    @Test
    @DisplayName("Teste Relatório Clientes mais lucrativos -  lista com um pedido")
    public void relatorioDeUmUnicoPedido() {

        listaPedidos.add(geraListaComUmPedido());
        Assertions.assertEquals(relatorio.gerarConteudo(listaPedidos), this.conteudoEsperadoUmUnicoPedido());
    }

    @Test
    @DisplayName("Teste Relatório Clientes mais lucrativos-  2 clientes mais lucrativos")
    public void deveriaApresentarDoisClientesMaisLucrativos() {
        Assertions.assertEquals(relatorio.gerarConteudo(geraListaComDoisClientesLucrativos()), conteudoEsperadoMaisDoisClientesLucrativos());
    }

    @Test
    @DisplayName("Teste Relatório Clientes mais lucrativos -  Cabeçalho")
    public void cabecalhoDoRelatorioDeClientesLucrativos() {
        Assertions.assertEquals(relatorio.gerarCabecalho(), "\n#### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS");

    }

    @Test
    @DisplayName("Teste Relatório Clientes mais lucrativos -  Cabeçalho Errado")
    public void cabecalhoDoRelatorioDeClientesLucrativosError() {
        Assertions.assertNotEquals(relatorio.gerarCabecalho(), "\n#### RELATÓRIO DE CLIENTES MENOS LUCRATIVOS");

    }

    private String conteudoEsperadoUmUnicoPedido() {
        return """
              \nCLIENTE: Bela
              Nº PEDIDOS: 1
              MONTANTE GASTO: %s
              """.formatted(FormatosImpressao.getRealFormat(new BigDecimal(300)));
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

    private List<Pedido> geraListaComDoisClientesLucrativos() {
        int year = 2022;
        int month = 4;
        int day = 29;

        listaPedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(300)).
                quantidade(6).
                data(LocalDate.of(year, month, day)).
                cliente("Bela").
                build());

        listaPedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(100)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Rafa").
                build());

        listaPedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(50)).
                quantidade(10).
                data(LocalDate.of(year, month, day)).
                cliente("Bela").
                build());

        listaPedidos.add(new PedidoBuilder().
                categoria("ALIMENTO").
                produto("MELANCIA").
                preco(new BigDecimal("2.5")).
                quantidade(10).
                data(LocalDate.of(year, month, day)).
                cliente("José").
                build());

        return listaPedidos;
    }

    private String conteudoEsperadoMaisDoisClientesLucrativos() {
        return """
                \nCLIENTE: Bela
                Nº PEDIDOS: 2
                MONTANTE GASTO: %s
                \nCLIENTE: Rafa
                Nº PEDIDOS: 1
                MONTANTE GASTO: %s
                """.formatted(FormatosImpressao.getRealFormat(new BigDecimal(2300)), FormatosImpressao.getRealFormat(new BigDecimal(100)));
    }
}