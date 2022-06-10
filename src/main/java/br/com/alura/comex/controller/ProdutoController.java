package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.DetalhesDeProdutoDto;
import br.com.alura.comex.controller.dto.ProdutoDto;
import br.com.alura.comex.controller.form.AtualizacaoProdutoForm;
import br.com.alura.comex.controller.form.ProdutoForm;
import br.com.alura.comex.entity.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<ProdutoDto> listar(){
        List<Produto> produtos = produtoRepository.findAll();
        return ProdutoDto.converter(produtos);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder){

        Produto produto = form.converter(categoriaRepository);
        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(produto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDeProdutoDto> buscarProdutoPorId(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) return ResponseEntity.ok(new DetalhesDeProdutoDto(produto.get()));

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoProdutoForm form){

        Optional<Produto> optional = produtoRepository.findById(id);

        if (optional.isPresent()){
            Produto produto = form.atualizar(id, produtoRepository);
            return ResponseEntity.ok(new ProdutoDto(produto));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Produto> optional = produtoRepository.findById(id);

        if (optional.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
