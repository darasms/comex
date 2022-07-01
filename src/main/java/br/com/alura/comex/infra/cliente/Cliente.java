package br.com.alura.comex.infra.cliente;


import br.com.alura.comex.infra.usuario.Usuario;
import br.com.alura.comex.infra.pedido.Pedido;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long cpf;

    private String telefone;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "id")
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToOne(optional = false, mappedBy = "cliente")
    private Usuario usuario;

    public Cliente() {
        super();
    }

    public Cliente(String nome, Long cpf, String telefone, Endereco endereco ) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public void adicionarPedido(Pedido pedido){
        pedido.setCliente(this);
        this.pedidos.add(pedido);
    }

    public BigDecimal getMontanteGasto(){
        return pedidos.stream().map(Pedido::getValorTotalPedido).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int quantidadePedidos(){
        return pedidos.size();
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Pedido> getListaDePedido() {
        return pedidos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setListaDePedido(List<Pedido> listaDePedido) {
        this.pedidos = listaDePedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
