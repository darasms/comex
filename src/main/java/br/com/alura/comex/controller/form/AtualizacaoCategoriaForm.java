package br.com.alura.comex.controller.form;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.enuns.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoCategoriaForm {

    @NotNull @NotEmpty @Length(min=2)
    private String nome;
    private StatusCategoria status;
    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.setNome(this.nome);
        return categoria;
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
