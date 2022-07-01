package br.com.alura.comex.adapter.dto.categorias;

import br.com.alura.comex.infra.categoria.CategoriaEntity;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import br.com.alura.comex.infra.enuns.StatusCategoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {

    private Long id;
    private String nome;
    private StatusCategoria status;

    private List<ProdutoEntity> produtoEntities;

    public CategoriaDto(CategoriaEntity categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
        this.produtoEntities = categoria.getProdutoEntities();
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

    public static List<CategoriaDto> converter(List<CategoriaEntity> categorias) {
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }
}
