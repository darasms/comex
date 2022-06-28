package br.com.alura.comex.controller;


import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.dto.DetalhesDaCategoriaDto;
import br.com.alura.comex.controller.form.AtualizacaoCategoriaForm;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.projecao.RelatorioPedidosPorCategoriaProjecao;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listar() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(CategoriaDto.converter(categorias));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriComponentsBuilder) {

        Categoria categoria = form.converter();
        categoriaRepository.save(categoria);

        URI uri = uriComponentsBuilder.path("/api/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDaCategoriaDto> buscarCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()) {
            return ResponseEntity.ok().body(new DetalhesDaCategoriaDto(categoria.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCategoriaForm form) {

//        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()){
            Categoria categoriaAtualizada = form.atualizar(id, categoriaRepository);

            return ResponseEntity.ok(new CategoriaDto(categoriaAtualizada));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {

        Optional<Categoria> optional = categoriaRepository.findById(id);

        if (optional.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/pedidos")
    @Cacheable(value = "relatorioPedidosPorCategoria")
    public ResponseEntity<List<RelatorioPedidosPorCategoriaProjecao>> pedidosVendidosPorCategoria(){

        List<RelatorioPedidosPorCategoriaProjecao> relatorioPedidosPorCategoria = pedidoRepository.findPedidosPorCategoria();

        return ResponseEntity.ok(relatorioPedidosPorCategoria);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizarStatus(@PathVariable Long id){

//        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (!categoria.isPresent()) return ResponseEntity.notFound().build();

        AtualizacaoCategoriaForm atualizacaoCategoriaForm = new AtualizacaoCategoriaForm();

        Categoria categoriaAtualizada = atualizacaoCategoriaForm.atualizarStatus(categoria.get());
        return ResponseEntity.ok(new CategoriaDto(categoriaAtualizada));
    }

}
