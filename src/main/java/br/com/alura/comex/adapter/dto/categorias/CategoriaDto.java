package br.com.alura.comex.adapter.dto.categorias;

import br.com.alura.comex.entity.categoria.Categoria;
import br.com.alura.comex.entity.produto.Produto;
import br.com.alura.comex.entity.enuns.StatusCategoria;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoriaDto {

    private Long id;
    private String nome;
    private StatusCategoria status;

    private List<Produto> produtoEntities;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
        this.produtoEntities = categoria.getProdutos();
    }

    public static List<CategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }
}
