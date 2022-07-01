package br.com.alura.comex.adapter.controller;

import br.com.alura.comex.adapter.dto.produto.DetalhesDeProdutoDto;
import br.com.alura.comex.adapter.dto.produto.ProdutoDto;
import br.com.alura.comex.adapter.form.AtualizacaoProdutoForm;
import br.com.alura.comex.adapter.form.ProdutoForm;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import br.com.alura.comex.infra.categoria.CategoriaDAO;
import br.com.alura.comex.repository.ProdutoRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaDAO categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> listar(@RequestParam(defaultValue = "0") int pagina){

        Pageable pegeable = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "nome"));

        Page<ProdutoEntity> produtos = produtoRepository.findAll(pegeable);

        Page<ProdutoDto> produtosDto = ProdutoDto.converterPagina(produtos);

        return ResponseEntity.ok().body(produtosDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder){

        ProdutoEntity produtoEntity = form.converter(categoriaRepository);
        produtoRepository.save(produtoEntity);

        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produtoEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(produtoEntity));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDeProdutoDto> buscarProdutoPorId(@PathVariable Long id){
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);

        if (produto.isPresent()) return ResponseEntity.ok(new DetalhesDeProdutoDto(produto.get()));

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoProdutoForm form){

        Optional<ProdutoEntity> optional = produtoRepository.findById(id);

        if (optional.isPresent()){
            ProdutoEntity produtoEntity = form.atualizar(id, produtoRepository, categoriaRepository);
            return ResponseEntity.ok(new ProdutoDto(produtoEntity));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<ProdutoEntity> optional = produtoRepository.findById(id);

        if (optional.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
