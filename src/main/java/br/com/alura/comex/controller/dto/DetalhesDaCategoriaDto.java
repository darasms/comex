package br.com.alura.comex.controller.dto;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.entity.enuns.StatusCategoria;

import java.util.List;

public class DetalhesDaCategoriaDto {

    private String nome;
    private StatusCategoria status;

    private List<Produto> produtos;

    public DetalhesDaCategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
        this.produtos = categoria.getProdutos();
    }

    public String getNome() {
        return nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
