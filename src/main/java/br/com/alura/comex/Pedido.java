package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;


public class Pedido {

    private String categoria;
    private String produto;
    private String cliente;

    private BigDecimal preco;
    private int quantidade;

    private LocalDate data;


    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }
    public BigDecimal getValorTotal(){
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
    public boolean isMaisBaratoQue(Pedido outroPedido) {
        if (outroPedido.preco.compareTo(preco) == 1) return false;
        return true;}

    public boolean isMaisCaroQue(Pedido outroPedido) {
        if (preco.compareTo(outroPedido.preco) ==1 )return false;
        return true;
    }

    public boolean isMesmoClient(Pedido outroPedido){
        if (this.cliente.compareTo(outroPedido.getCliente()) ==1) return false;
        return true;
    }


    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

}
