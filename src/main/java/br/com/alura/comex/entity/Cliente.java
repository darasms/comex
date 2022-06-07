package br.com.alura.comex.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long cpf;

    private Long telefone;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "id")
    private List<Pedido> listaDePedido;

    public Cliente(String nome, Long cpf, Long telefone, Endereco endereco, List<Pedido> listaDePedido ) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.listaDePedido = listaDePedido;
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

    public Long getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Pedido> getListaDePedido() {
        return listaDePedido;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setListaDePedido(List<Pedido> listaDePedido) {
        this.listaDePedido = listaDePedido;
    }
}
