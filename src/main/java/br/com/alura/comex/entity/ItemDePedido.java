package br.com.alura.comex.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "preco_unitario")
    @NotNull
    private BigDecimal precoUnitario;

    @NotNull
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedidoId;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    @NotNull
    private Produto produtoId;

    private BigDecimal desconto;

    @OneToOne
    @JoinColumn(name = "tipo_desconto", referencedColumnName = "id")
    private TipoDesconto tipoDesconto;

    public ItemDePedido(BigDecimal precoUnitario, Integer quantidade, Pedido pedidoId, Produto produtoId, BigDecimal desconto, TipoDesconto tipoDesconto) {
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
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
        return pedidoId;
    }

    public Produto getProdutoId() {
        return produtoId;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }
}
