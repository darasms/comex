package br.com.alura.comex.adapter.controller.pedido;

import br.com.alura.comex.adapter.dto.pedido.DetalhamentoPedidoDto;
import br.com.alura.comex.adapter.dto.pedido.DetalhesPedidoDto;
import br.com.alura.comex.adapter.dto.pedido.PedidoDto;
import br.com.alura.comex.adapter.form.PedidoFrom;
import br.com.alura.comex.infra.pedido.Pedido;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.ItemDePedidoRepository;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemDePedidoRepository itemDePedidoRepository;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar(@PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC, sort = "data") Pageable pageable) {

        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
        Page<PedidoDto> paginaPedidos = PedidoDto.converter(pedidos);

        return ResponseEntity.ok().body(paginaPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoPedidoDto> buscarPedidoPorId(@PathVariable Long id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);

        if (optionalPedido.isPresent()) {
            return ResponseEntity.ok().body(new DetalhamentoPedidoDto(optionalPedido.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "relatorioPedidosPorCategoria", allEntries = true)
    public ResponseEntity<DetalhesPedidoDto> cadastrar(@RequestBody @Valid PedidoFrom form, UriComponentsBuilder uriComponentsBuilder) {
        Pedido pedido = form.converter(clienteRepository, produtoRepository);

        pedidoRepository.save(pedido);

        URI uri = uriComponentsBuilder.path("/api/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesPedidoDto(pedido));

    }
}
