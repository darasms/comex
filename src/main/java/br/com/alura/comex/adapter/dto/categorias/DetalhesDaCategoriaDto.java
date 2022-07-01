package br.com.alura.comex.adapter.dto.categorias;

import br.com.alura.comex.infra.categoria.CategoriaEntity;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import br.com.alura.comex.infra.enuns.StatusCategoria;

import java.util.List;
import java.util.stream.Collectors;

public class DetalhesDaCategoriaDto {

    private String nome;
    private StatusCategoria status;

    private List<String> produtos;

    public DetalhesDaCategoriaDto(CategoriaEntity categoria) {
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
        this.produtos = categoria.getProdutoEntities().stream().map(ProdutoEntity::getNome).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public StatusCategoria getStatus() {
        return status;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return "DetalhesDaCategoriaDto{" +
                "nome='" + nome + '\'' +
                ", status=" + status +
                ", produtos=" + produtos +
                '}';
    }
}
