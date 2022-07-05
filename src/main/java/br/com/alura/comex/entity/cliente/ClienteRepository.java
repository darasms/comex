package br.com.alura.comex.entity.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteRepository {
    Cliente cadastrarCliente(Cliente cliente);

    List<Cliente> listarTodosClientes();

    Cliente buscarPorCpf(String numeroCpf);

    Page<Cliente> listarTodosClientesPaginados(Pageable pageable);

    Cliente buscarPorId(Long id);

}
