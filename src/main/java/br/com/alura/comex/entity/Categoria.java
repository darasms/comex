package br.com.alura.comex.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @Enumerated(EnumType.STRING)
    private StatusCategoria status;

    @OneToMany
    private List<Pedido> produtos;

    public Categoria(String nome, StatusCategoria status) {
        this.nome = nome;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public List<Pedido> getProdutos() {
        return produtos;
    }
}
