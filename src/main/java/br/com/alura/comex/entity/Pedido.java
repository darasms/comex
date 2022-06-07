package br.com.alura.comex.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data = LocalDate.now();

    @ManyToOne(optional = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemDePedido> listaItem;

    private BigDecimal desconto;

    @ManyToMany()
    private List<TipoDesconto> tipoDesconto;

    public Pedido(Cliente cliente, List<ItemDePedido> listaItem, BigDecimal desconto, List<TipoDesconto> tipoDesconto) {
        this.cliente = cliente;
        this.listaItem = listaItem;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemDePedido> getListaItem() {
        return listaItem;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public List<TipoDesconto> getTipoDesconto() {
        return tipoDesconto;
    }
}
