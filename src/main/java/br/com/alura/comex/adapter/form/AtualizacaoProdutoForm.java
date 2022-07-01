package br.com.alura.comex.adapter.form;

import br.com.alura.comex.infra.categoria.CategoriaEntity;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import br.com.alura.comex.infra.categoria.CategoriaDAO;
import br.com.alura.comex.repository.ProdutoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Optional;

public class AtualizacaoProdutoForm {

    @NotNull
    @NotEmpty
    @Size(min = 2)
    private String nome;

    private String descricao;
    @NotNull
    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    @NotNull
    private Long categoria;


    public ProdutoEntity atualizar(Long id, ProdutoRepository produtoRepository, CategoriaDAO categoriaRepository) {

        Optional<ProdutoEntity> produto = produtoRepository.findById(id);
        produto.get().setNome(this.nome);
        produto.get().setDescricao(this.descricao);
        produto.get().setPrecoUnitario(this.precoUnitario);
        produto.get().setQuantidadeEstoque(produto.get().getQuantidadeEstoque() + this.quantidadeEstoque);
        Optional<CategoriaEntity> novaCategoria = categoriaRepository.findById(categoria);

        if(novaCategoria.isPresent()){
            produto.get().setCategoria(novaCategoria.get());
        }else{
            throw new RuntimeException("Categoria não foi encontrada!");
        }

        return produto.get();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }
}
