package br.com.alura.comex.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @ManyToOne
    private Categoria categoriaId;

    public Produto(String name, String descricao, BigDecimal precoUnitario, Integer quantidadeEstoque, Categoria categoriaId) {
        this.name = name;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoriaId = categoriaId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }
}
