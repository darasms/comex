package br.com.alura.comex.adapter.form;

import br.com.alura.comex.entity.categoria.Categoria;
import br.com.alura.comex.infra.enuns.StatusCategoria;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class CategoriaForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria converterEmCategoria() {
        return Categoria.builder()
                .nome(this.nome)
                .status(StatusCategoria.ATIVA)
                .produtos(new ArrayList<>())
                .build();
    }
}
