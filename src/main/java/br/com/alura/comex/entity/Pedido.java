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

    @ManyToOne
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private List<ItemDePedido> listaPedido;

    private BigDecimal desconto;

    @ManyToMany
    @JoinColumn(name = "tipo_desconto", referencedColumnName = "id")
    private List<TipoDesconto> tipoDesconto;

    public Pedido(Cliente cliente, List<ItemDePedido> listaPedido, BigDecimal desconto, List<TipoDesconto> tipoDesconto) {
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
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
