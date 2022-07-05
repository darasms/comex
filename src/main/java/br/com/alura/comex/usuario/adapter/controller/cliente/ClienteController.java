package br.com.alura.comex.usuario.adapter.controller.cliente;

import br.com.alura.comex.usuario.adapter.dto.cliente.ClienteDto;
import br.com.alura.comex.usuario.adapter.dto.cliente.ResumoClienteDto;
import br.com.alura.comex.usuario.adapter.form.ClienteForm;
import br.com.alura.comex.usuario.entity.cliente.Cliente;
import br.com.alura.comex.usuario.infra.cliente.ClienteRepositoryImpl;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepositoryImpl clienteDAO;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        Cliente cliente = form.converterParaCliente();
        clienteDAO.cadastrarCliente(cliente);

        URI uri = uriBuilder.path("/api/cliente/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<ResumoClienteDto>> listar(@RequestParam(defaultValue = "0") int pagina) {

        Pageable pageable = PageRequest.of(pagina, 5, Sort.Direction.ASC, "nome");
        Page<Cliente> clientes = clienteDAO.listarTodosClientesPaginados(pageable);

        Page<ResumoClienteDto> resumoClientes = ResumoClienteDto.converter(clientes);

        return ResponseEntity.ok().body(resumoClientes);
    }

}
