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

    @OneToMany
    @Column(name = "pedidos")
    private List<Pedido> listaDePedido;

    public Cliente(String nome, Long cpf, Long telefone, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
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
}
