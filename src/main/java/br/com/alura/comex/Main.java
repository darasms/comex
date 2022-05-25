package br.com.alura.comex;


import br.com.alura.comex.exception.DomainException;
import br.com.alura.comex.menu.GerarMenu;
import br.com.alura.comex.menu.OpcoesEscolha;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.processador.ProcessadorXML;
import br.com.alura.comex.relatorio.GeradorRelatorio;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws IOException {

        String xmlPedido = "src/main/resources/pedidos.xml";
        List<Pedido> pedidos = new ProcessadorXML().getPedidos(xmlPedido);


        GerarMenu menu = new GerarMenu();
        int tipoRelatorioEscolhido = menu.opcoesRelatorio();

        Map<Integer, GeradorRelatorio> opcoes = OpcoesEscolha.getOpcoesEscolha();

        if (tipoRelatorioEscolhido > opcoes.size()) throw new DomainException("Opção invválida");
        menu.imprimeRelatorio(opcoes.get(tipoRelatorioEscolhido), pedidos);


    }

}
