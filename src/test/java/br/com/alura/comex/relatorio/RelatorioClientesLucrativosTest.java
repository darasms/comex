package br.com.alura.comex.relatorio;


import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.PedidoBuilder;
import br.com.alura.comex.utils.FormatosImpressao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class RelatorioClientesLucrativosTest {

    private static GeradorRelatorio relatorio;
    private static List<Pedido> listaPedidos;

    @BeforeAll
    public static void setup() {
        relatorio = new RelatorioClientesLucrativos();
        listaPedidos = new ArrayList<>();
    }

    @Test
    @DisplayName("Teste Relatório Clientes mais lucrativos - sem lista de pedidos")
    public void relatorioSemListaDePedidos() {
        List<Pedido> listaPedidosVazia = new ArrayList<>();;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            relatorio.gerarConteudo(listaPedidosVazia);
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

        StringBuilder conteudo = new StringBuilder();

        conteudo.append("\nCLIENTE: " + "Bela");
        conteudo.append("\nNº PEDIDOS: " + 1);
        conteudo.append("\nMONTANTE GASTO: " + FormatosImpressao.getRealFormat(new BigDecimal(300)) + "\n");

        return conteudo.toString();
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

        List<Pedido> listaDePedidos = new ArrayList<>();

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(300)).
                quantidade(6).
                data(LocalDate.of(year, month, day)).
                cliente("Bela").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(100)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Rafa").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(50)).
                quantidade(10).
                data(LocalDate.of(year, month, day)).
                cliente("Bela").
                build());

        listaDePedidos.add(new PedidoBuilder().
                categoria("ALIMENTO").
                produto("MELANCIA").
                preco(new BigDecimal("2.5")).
                quantidade(10).
                data(LocalDate.of(year, month, day)).
                cliente("José").
                build());

        return listaDePedidos;
    }

    private String conteudoEsperadoMaisDoisClientesLucrativos() {
        StringBuilder conteudo = new StringBuilder();

        conteudo.append("\nCLIENTE: " + "Bela");
        conteudo.append("\nNº PEDIDOS: " + 2);
        conteudo.append("\nMONTANTE GASTO: " + FormatosImpressao.getRealFormat(new BigDecimal(2300)) + "\n");

        conteudo.append("\nCLIENTE: " + "Rafa");
        conteudo.append("\nNº PEDIDOS: " + 1);
        conteudo.append("\nMONTANTE GASTO: " + FormatosImpressao.getRealFormat(new BigDecimal(100)) + "\n");


        return conteudo.toString();
    }
}