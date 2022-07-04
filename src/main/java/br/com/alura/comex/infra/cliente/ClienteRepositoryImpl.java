package br.com.alura.comex.infra.cliente;

import br.com.alura.comex.entity.cliente.Cliente;
import br.com.alura.comex.entity.cliente.ClienteRepository;

import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteDAO clienteDAO;

    public ClienteRepositoryImpl(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = clienteDAO.save(ClienteEntity.converter(cliente));
        return clienteEntity.paraCliente();
    }

    @Override
    public List<Cliente> listarTodosClientes() {
        return null;
    }

    @Override
    public Cliente buscarPorCpf(String numeroCpf) {
        return null;
    }
}
