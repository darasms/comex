//package br.com.alura.comex.adapter.controller.cliente;
//
//import br.com.alura.comex.adapter.dto.cliente.ClienteDto;
//import br.com.alura.comex.adapter.dto.cliente.ResumoClienteDto;
//import br.com.alura.comex.adapter.form.ClienteForm;
//import br.com.alura.comex.entity.cliente.Cliente;
//import br.com.alura.comex.infra.cliente.ClienteEntity;
//import br.com.alura.comex.infra.cliente.ClienteRepositoryImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import javax.transaction.Transactional;
//import javax.validation.Valid;
//import java.net.URI;
//
//@RestController
//@RequestMapping("api/clientes")
//public class ClienteController {
//
//    @Autowired
//    private ClienteRepositoryImpl clienteDAO;
//
//    @PostMapping
//    @Transactional
//    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder){
//        Cliente cliente = form.converterParaCliente();
//        clienteDAO.cadastrarCliente(clienteEntity);
//
//        URI uri = uriBuilder.path("/api/cliente/{id}").buildAndExpand(clienteEntity.getId()).toUri();
//
//        return ResponseEntity.created(uri).body(new ClienteDto(clienteEntity));
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<ResumoClienteDto>> listar(@RequestParam(defaultValue = "0") int pagina){
//
//        Pageable pageable = PageRequest.of(pagina, 5, Sort.Direction.ASC, "nome");
//        Page<ClienteEntity> clientes = clienteDAO.findAll(pageable);
//
//        Page<ResumoClienteDto> resumoClientes = ResumoClienteDto.converter(clientes);
//
//        return ResponseEntity.ok().body(resumoClientes);
//    }
//
//}
