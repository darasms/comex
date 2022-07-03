package br.com.alura.comex.infra.categoria;

import br.com.alura.comex.entity.categoria.Categoria;
import br.com.alura.comex.entity.produto.Produto;
import br.com.alura.comex.infra.enuns.StatusCategoria;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCategoria status = StatusCategoria.ATIVA;

    @OneToMany(mappedBy = "categoria")
    private List<ProdutoEntity> produtoEntities = new ArrayList<>();

    public CategoriaEntity(String nome) {
        this.nome = nome;
    }

    public static CategoriaEntity converter(Categoria categoria) {
        return new CategoriaEntity(categoria.getNome());

    }

    public List<Produto> toProdutos() {
        return this.produtoEntities.stream().map(ProdutoEntity::toProduto).toList();
    }

    public Categoria toCategoria() {
        return Categoria.builder()
                .id(this.id)
                .nome(this.nome)
                .status(this.status)
                .produtos(toProdutos())
                .build();
    }


    public void adicionarProduto(ProdutoEntity produtoEntity) {
        produtoEntity.setCategoria(this);
        this.produtoEntities.add(produtoEntity);
    }

}