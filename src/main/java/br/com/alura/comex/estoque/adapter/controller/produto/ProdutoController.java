package br.com.alura.comex.estoque.adapter.controller.produto;

import br.com.alura.comex.estoque.adapter.controller.produto.dto.DetalhesDeProdutoDto;
import br.com.alura.comex.estoque.adapter.controller.produto.dto.ProdutoDto;
import br.com.alura.comex.estoque.adapter.controller.produto.form.AtualizacaoProdutoForm;
import br.com.alura.comex.estoque.adapter.controller.produto.form.ProdutoForm;
import br.com.alura.comex.estoque.entity.produto.Produto;
import br.com.alura.comex.compartilhado.infra.categoria.CategoriaRepositoryImpl;
import br.com.alura.comex.estoque.infra.produto.ProdutoEntity;
import br.com.alura.comex.estoque.infra.produto.ProdutoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepositoryImpl produtoRepository;

    @Autowired
    private CategoriaRepositoryImpl categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> listar(@RequestParam(defaultValue = "0") int pagina) {

        Pageable pegeable = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "nome"));

        Page<Produto> produtos = produtoRepository.listarProdutosCadastradosPaginados(pegeable);

        Page<ProdutoDto> produtosDto = ProdutoDto.converterPagina(produtos);

        return ResponseEntity.ok().body(produtosDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {

        Produto prod = form.converterEmProduto(categoriaRepository);
        Produto produto = produtoRepository.cadastrarProduto(prod);

        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getCodigoProduto()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(produto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDeProdutoDto> buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoRepository.buscarProdutoPorCodProduto(id);
        return ResponseEntity.ok(new DetalhesDeProdutoDto(ProdutoEntity.converter(produto)));

    }


    @PutMapping("/{codigoProduto}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long codigoProduto, @RequestBody @Valid AtualizacaoProdutoForm form) {

        Produto produto = produtoRepository.atualizarProduto(codigoProduto, form.paraProduto(categoriaRepository));

        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        produtoRepository.excluirProduto(id);

        return ResponseEntity.ok().build();
    }
}
