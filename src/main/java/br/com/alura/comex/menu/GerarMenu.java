package br.com.alura.comex.menu;

import br.com.alura.comex.exception.DomainException;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.relatorio.GeradorRelatorio;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GerarMenu {

    public void imprimeRelatorio(List<Pedido> pedidos){
        int tipoRelatorioEscolhido;
        do {
            this.imprimeOpcoesRelatorio();
            tipoRelatorioEscolhido = this.opcoesRelatorio();

            Map<Integer, GeradorRelatorio> opcoes = OpcoesEscolha.getOpcoesEscolha();
            if (!opcoes.containsKey(tipoRelatorioEscolhido)) throw new DomainException("Opção inválida");

            this.executa(opcoes.get(tipoRelatorioEscolhido), pedidos);
        }while(tipoRelatorioEscolhido != 0);

    }
    public void imprimeOpcoesRelatorio(){
        System.out.println("Qual relatorio voc deseja? ");
        System.out.println("[1] Relatorio Sintético ");
        System.out.println("[2] Relatório Clientes Fiéis");
        System.out.println("[3] Relatório de Vendas por Categoria");
        System.out.println("[4] Relatório de produtos mais vendidos ");
        System.out.println("[5] Relatório de produtos mais caros de cada Categoria ");
        System.out.println("[6] Relatório de clientes mais lucrativos");
        System.out.println("[0] Sair");
    }
    public int opcoesRelatorio() {
        return new Scanner(System.in).nextInt();
    }

    public void executa(GeradorRelatorio tipoRelatorio, List<Pedido> pedidos ){
        if (tipoRelatorio == null) System.exit(0);
        tipoRelatorio.gerarRelatorio(pedidos);
    }
}
