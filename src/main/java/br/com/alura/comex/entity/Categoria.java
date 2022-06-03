package br.com.alura.comex.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String nome;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    public Categoria(String nome, StatusEnum status) {
        this.nome = nome;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public StatusEnum getStatus() {
        return status;
    }
}
