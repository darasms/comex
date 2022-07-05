package br.com.alura.comex.pedido.adapter.form;

import br.com.alura.comex.pedido.entity.categoria.Categoria;
import br.com.alura.comex.pedido.entity.enuns.StatusCategoria;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Getter @Setter
public class CategoriaForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    public Categoria converterEmCategoria() {
        return Categoria.builder()
                .nome(this.nome)
                .status(StatusCategoria.ATIVA)
                .produtos(new ArrayList<>())
                .build();
    }
}
