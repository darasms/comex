package br.com.alura.comex;

import java.util.List;

public class Relatorio {
    protected List<Pedido>  listaDePedidos;

    public Relatorio(List<Pedido> listaDePedidos) {
        if (listaDePedidos == null || listaDePedidos.isEmpty()) throw new IllegalArgumentException("Lista de pedidos nula ou vazia!");
        this.listaDePedidos = listaDePedidos;
    }
}
