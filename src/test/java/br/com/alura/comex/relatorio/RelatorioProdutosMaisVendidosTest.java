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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioProdutosMaisVendidosTest {

    @Test
    @DisplayName("Teste Relatório Produtos Mais Vendidos - sem lista de pedidos")
    public void relatorioSemListaDePedidos() {
        GeradorRelatorio relatorio = new RelatorioProdutosMaisVendidos();
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
        GeradorRelatorio relatorio = new RelatorioProdutosMaisVendidos();
        String resultado = relatorio.gerarConteudo(listaPedidos);

        //3) ASSERT
        Assertions.assertEquals(resultado, this.conteudoEsperadoUmUnicoPedido());
    }

    private String conteudoEsperadoUmUnicoPedido(){
        StringBuilder conteudo  = new StringBuilder();

        conteudo.append("\nPRODUTO: " + "Mouse" + "\nQUANTIDADE: " + 1 + "\n");

        return conteudo.toString();
    }
}