package br.com.alura.comex.entity.cliente;

import java.util.List;

public interface ClienteRepository {
    void cadastrarCliente(Cliente cliente);

    List<Cliente> listarTodosClientes();

    Cliente buscarPorCpf(String numeroCpf);

}
