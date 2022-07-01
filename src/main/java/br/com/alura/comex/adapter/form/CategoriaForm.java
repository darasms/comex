package br.com.alura.comex.adapter.form;

import br.com.alura.comex.infra.categoria.CategoriaEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public CategoriaEntity converter() {
        return new CategoriaEntity(nome);
    }
}
