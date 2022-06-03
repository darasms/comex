package br.com.alura.comex.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    private BigDecimal desconto;

    @OneToOne
    @JoinColumn(name = "tipo_desconto", referencedColumnName = "id")
    private TipoDesconto tipoDesconto;

    public ItemDePedido(BigDecimal precoUnitario, Integer quantidade, Pedido pedidoId, Produto produtoId, BigDecimal desconto, TipoDesconto tipoDesconto) {
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.pedido = pedidoId;
        this.produto = produtoId;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Pedido getPedidoId() {
        return pedido;
    }

    public Produto getProdutoId() {
        return produto;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }
}
