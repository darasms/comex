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


public class RelatorioVendasCategoriaTest {

    private static GeradorRelatorio relatorio;
    private static List<Pedido> listaPedidos;

    @BeforeAll
    public static void setup() {
        relatorio = new RelatorioVendasCategoria();
        listaPedidos = new ArrayList<>();
    }

    @Test
    @DisplayName("Teste Relatório Vendas por Categoria - sem lista de pedidos")
    public void relatorioSemListaDePedidos() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            relatorio.gerarConteudo(listaPedidos);
        });

    }

    @Test
    public void relatorioDeUmUnicoPedido() {

        listaPedidos.add(geraListaComUmPedido());
        Assertions.assertEquals(relatorio.gerarConteudo(listaPedidos), this.conteudoEsperadoUmUnicoPedido());
    }

    private String conteudoEsperadoUmUnicoPedido() {

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("\nCATEGORIA: " + "INFORMATICA" + "\nQUANTIDADE VENDIDA: " + 1);
        conteudo.append("\nMONTANTE: " + FormatosImpressao.getRealFormat(new BigDecimal(300)) + "\n");

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

}
