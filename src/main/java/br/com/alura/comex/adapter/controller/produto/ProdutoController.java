package br.com.alura.comex.adapter.controller.produto;

import br.com.alura.comex.adapter.dto.produto.DetalhesDeProdutoDto;
import br.com.alura.comex.adapter.dto.produto.ProdutoDto;
import br.com.alura.comex.adapter.form.AtualizacaoProdutoForm;
import br.com.alura.comex.adapter.form.ProdutoForm;
import br.com.alura.comex.entity.produto.Produto;
import br.com.alura.comex.infra.categoria.CategoriaRepositoryImpl;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import br.com.alura.comex.infra.produto.ProdutoRepositoryImpl;
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

        Page<ProdutoEntity> produtos = produtoRepository.listarProdutosCadastradosPaginados(pegeable);

        Page<ProdutoDto> produtosDto = ProdutoDto.converterPagina(produtos);

        return ResponseEntity.ok().body(produtosDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {

        Produto produto = produtoRepository.cadastrarProduto(form.converterEmProduto(categoriaRepository));

        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getCodigoProduto()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(ProdutoEntity.converter(produto)));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDeProdutoDto> buscarProdutoPorId(@PathVariable Long id){
        Produto produto = produtoRepository.buscarProdutoPorCodProduto(id);
        return ResponseEntity.ok(new DetalhesDeProdutoDto(ProdutoEntity.converter(produto)));

    }



    @PutMapping("/{codigoProduto}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long codigoProduto, @RequestBody @Valid AtualizacaoProdutoForm form){

        Produto produto = produtoRepository.atualizarProduto(codigoProduto, form.paraProduto(categoriaRepository));

        return ResponseEntity.ok(new ProdutoDto(ProdutoEntity.converter(produto)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        produtoRepository.excluirProduto(id);

        return ResponseEntity.ok().build();
    }
}
