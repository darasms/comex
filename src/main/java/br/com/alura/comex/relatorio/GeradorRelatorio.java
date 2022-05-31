package br.com.alura.comex.relatorio;

import br.com.alura.comex.model.Pedido;

import java.util.List;

public abstract class GeradorRelatorio {

    public abstract String gerarCabecalho();
    public abstract String gerarConteudo(List<Pedido> listaDePedidos);

}
