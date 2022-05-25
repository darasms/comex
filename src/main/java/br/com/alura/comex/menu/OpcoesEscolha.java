package br.com.alura.comex.menu;

import br.com.alura.comex.relatorio.*;

import java.util.Map;
import java.util.TreeMap;

public class OpcoesEscolha {

    private static Map<Integer, GeradorRelatorio>  opcoesEscolha;
    public static Map<Integer, GeradorRelatorio> getOpcoesEscolha(){
        Map<Integer, GeradorRelatorio> opcoesEscolha = new TreeMap<>();
        opcoesEscolha.put(1, new RelatorioSintetico());
        opcoesEscolha.put(2, new RelatorioClientesFieis());
        opcoesEscolha.put(3, new RelatorioVendasCategoria());
        opcoesEscolha.put(4, new RelatorioProdutosMaisVendidos());
        opcoesEscolha.put(5, new RelatorioProdutoMaisCaroCategoria());
        opcoesEscolha.put(6, new RelatorioClientesLucrativos());
        opcoesEscolha.put(0, null);
        return opcoesEscolha;
    }

    public static void adicionar(Integer chaveAcesso, GeradorRelatorio novoRelatorio) {
        opcoesEscolha.put(chaveAcesso, novoRelatorio);
    }

}
