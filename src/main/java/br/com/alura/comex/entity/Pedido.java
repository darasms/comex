package br.com.alura.comex.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Cliente clientId;

    @OneToMany
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    @NotNull
    private List<ItemDePedido> listaPedido;

    private BigDecimal desconto;

    @ManyToMany
    @JoinColumn(name = "tipo_desconto", referencedColumnName = "id")
    private List<TipoDesconto> tipoDesconto;

    public Pedido(Cliente clientId, List<ItemDePedido> listaPedido, BigDecimal desconto, List<TipoDesconto> tipoDesconto) {
        this.clientId = clientId;
        this.listaPedido = listaPedido;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Cliente getClientId() {
        return clientId;
    }

    public List<ItemDePedido> getListaPedido() {
        return listaPedido;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public List<TipoDesconto> getTipoDesconto() {
        return tipoDesconto;
    }
}
