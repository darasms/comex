package br.com.alura.comex.infra.categoria;

import br.com.alura.comex.entity.categoria.Categoria;
import br.com.alura.comex.entity.produto.Produto;
import br.com.alura.comex.infra.enuns.StatusCategoria;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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


    public static CategoriaEntity converter(Categoria categoria) {

        return CategoriaEntity.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .status(categoria.getStatus())
                .produtoEntities(categoria.getProdutos().stream().map(ProdutoEntity::converter).toList())
                .build();
    }

    public List<Produto> toProdutos() {
        return this.produtoEntities.stream().map(ProdutoEntity::paraProduto).toList();
    }

    public Categoria toCategoria() {
        return Categoria.builder()
                .id(this.id)
                .nome(this.nome)
                .status(this.status)
                .produtos(new ArrayList<>())
                .build();
    }


    public void adicionarProduto(ProdutoEntity produtoEntity) {
        produtoEntity.setCategoria(this);
        this.produtoEntities.add(produtoEntity);
    }

}