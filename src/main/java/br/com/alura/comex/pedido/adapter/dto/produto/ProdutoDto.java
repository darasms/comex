package br.com.alura.comex.pedido.adapter.dto.produto;

import br.com.alura.comex.pedido.adapter.dto.categorias.CategoriaDto;
import br.com.alura.comex.pedido.entity.produto.Produto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class ProdutoDto {


    private Long codigoProduto;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private int quantidadeEstoque;

    private CategoriaDto categoria;

    public ProdutoDto(Produto produtoEntity) {
        this.codigoProduto = produtoEntity.getCodigoProduto();
        this.nome = produtoEntity.getNome();
        this.descricao = produtoEntity.getDescricao();
        this.precoUnitario = produtoEntity.getPrecoUnitario();
        this.quantidadeEstoque = produtoEntity.getQuantidadeEstoque();
        this.categoria = new CategoriaDto(produtoEntity.getCategoria());
    }


    public static List<ProdutoDto> converter(Page<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).toList();
    }
    public static Page<ProdutoDto> converterPagina(Page<Produto> produtos) {
        return produtos.map(ProdutoDto::new);
    }

}
