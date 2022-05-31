package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.FormatosImpressao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RelatorioVendasCategoriaTest {

    @Test
    @DisplayName("Teste Relatório Vendas por Categoria - sem lista de pedidos")
    public void relatorioSemListaDePedidos() {
        GeradorRelatorio relatorio = new RelatorioVendasCategoria();
        List<Pedido> listaPedidos = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            relatorio.gerarConteudo(listaPedidos);
        });

    }

    @Test
    public void relatorioDeUmUnicoPedido(){
        //1) ARRANGE
        Pedido pedido = new Pedido("INFORMATICA", "Mouse", new BigDecimal(300), 1,  LocalDate.of(2022,04,29),"Bela");
        List<Pedido> listaPedidos = new ArrayList<>();
        listaPedidos.add(pedido);

        //2) ACT
        GeradorRelatorio relatorio = new RelatorioVendasCategoria();
        String resultado = relatorio.gerarConteudo(listaPedidos);

        //3) ASSERT
        Assertions.assertEquals(resultado, this.conteudoEsperadoUmUnicoPedido());
    }

    private String conteudoEsperadoUmUnicoPedido(){
        StringBuilder conteudo  = new StringBuilder();

        conteudo.append("\nCATEGORIA: " + "INFORMATICA" + "\nQUANTIDADE VENDIDA: " + 1);
        conteudo.append("\nMONTANTE: " +  FormatosImpressao.getRealFormat(new BigDecimal(300)) + "\n");

        return conteudo.toString();
    }

}
