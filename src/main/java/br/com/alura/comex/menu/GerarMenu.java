package br.com.alura.comex.menu;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.relatorio.GeradorRelatorio;

import java.util.List;
import java.util.Scanner;

public class GerarMenu {

    public int opcoesRelatorio(){
        Scanner s = new Scanner(System.in);

        System.out.println("Qual relatorio voc deseja? ");
        System.out.println("[1] Relatorio Sintético ");
        System.out.println("[2] Relatório Clientes Fiéis");
        System.out.println("[3] Relatório de Vendas por Categoria");
        System.out.println("[4] Relatório de produtos mais vendidos ");
        System.out.println("[5] Relatório de produtos mais caros de cada Categoria ");
        System.out.println("[6] Relatório de clientes mais lucrativos");
        System.out.println("[0] Sair");

        return s.nextInt();
    }

    public void imprimeRelatorio(GeradorRelatorio tipoRelatorio, List<Pedido> pedidos ){
        if (tipoRelatorio == null) System.exit(0);
        tipoRelatorio.gerarRelatorio(pedidos);
    }
}
