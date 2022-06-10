package br.com.alura.comex.controller.form;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.enuns.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AtualizacaoCategoriaForm {

    @NotNull @NotEmpty @Length(min=2)
    private String nome;
    private StatusCategoria status;
    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        categoria.get().setNome(this.nome);
        return categoria.get();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public void setStatus(StatusCategoria status) {
        this.status = status;
    }
}
