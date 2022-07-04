package br.com.alura.comex.infra.ItemDePedido;

import br.com.alura.comex.entity.enuns.TipoDescontoItem;
import br.com.alura.comex.infra.pedido.PedidoEntity;
import br.com.alura.comex.infra.produto.ProdutoEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PedidoEntity pedidoEntity;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProdutoEntity produtoEntity;

    @Column(nullable = false)
    private BigDecimal desconto = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private TipoDescontoItem tipoDesconto = TipoDescontoItem.NENHUM;

    public ItemDePedido() {
        super();
    }

    public ItemDePedido(Integer quantidade, ProdutoEntity produtoEntity) {
        this.quantidade = quantidade;
        this.produtoEntity = produtoEntity;
        this.precoUnitario = produtoEntity.getPrecoUnitario();
    }


    public BigDecimal getValorTotalItem() {
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
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

    public PedidoEntity getPedidoId() {
        return pedidoEntity;
    }

    public ProdutoEntity getProdutoId() {
        return produtoEntity;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public TipoDescontoItem getTipoDesconto() {
        return tipoDesconto;
    }

    public void setPedido(PedidoEntity pedidoEntity) {
        this.pedidoEntity = pedidoEntity;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public void setTipoDesconto(TipoDescontoItem tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }
}
