package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.DetalhamentoPedidoDto;
import br.com.alura.comex.controller.dto.PedidoDto;
import br.com.alura.comex.controller.form.PedidoFrom;
import br.com.alura.comex.entity.Pedido;
import br.com.alura.comex.repository.PedidoRepository;
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
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listar(@RequestParam(defaultValue = "0") int pagina){

        Pageable pageable = PageRequest.of(pagina, 5, Sort.Direction.DESC, "data");
        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
        Page<PedidoDto> paginaPedidos = PedidoDto.converter(pedidos);

        return ResponseEntity.ok().body(paginaPedidos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoPedidoDto> buscarPedidoPorId(@PathVariable Long id){
        Optional<Pedido> optionalPedido= pedidoRepository.findById(id);

        if (optionalPedido.isPresent()){
            return ResponseEntity.ok().body(new DetalhamentoPedidoDto(optionalPedido.get()));
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoFrom form, UriComponentsBuilder uriComponentsBuilder){
        Pedido pedido = form.converter();
        pedidoRepository.save(pedido);

        URI uri = uriComponentsBuilder.path("/api/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(new PedidoDto(pedido));

    }


}
