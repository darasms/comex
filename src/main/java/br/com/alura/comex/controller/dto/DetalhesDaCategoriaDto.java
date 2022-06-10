package br.com.alura.comex.controller.dto;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.enuns.StatusCategoria;

public class DetalhesDaCategoriaDto {

    private String nome;
    private StatusCategoria status;

    public DetalhesDaCategoriaDto(Categoria categoria) {
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
    }

    public String getNome() {
        return nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }
}
