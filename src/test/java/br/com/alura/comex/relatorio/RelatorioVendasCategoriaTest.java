package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class RelatorioVendasCategoriaTeste {

    @Test
    public void relatorioSemListaDePedidos(){
        GeradorRelatorio relatorio = new RelatorioVendasCategoria();
        List<Pedido> listaPedidos = new ArrayList<>();
        relatorio.gerarRelatorio(listaPedidos);

    }
}
