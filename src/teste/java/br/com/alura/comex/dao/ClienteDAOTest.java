package br.com.alura.comex.dao;


import br.com.alura.comex.entity.Cliente;
import br.com.alura.comex.entity.Endereco;
import br.com.alura.comex.entity.builder.ClienteBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class ClienteDAOTest {

    @Mock
    private ClienteDAO clienteDAO;

    @Test
    public void deveriaCadastrar() {

//        EntityManager em = Mockito.mock(EntityManager.class);
//        Mockito.doNothing().when(em).persist(Mockito.any());

        clienteDAO = Mockito.mock(ClienteDAO.class);

        Endereco endereco = new Endereco("Av. quinta", "424", "", "Santa Genebra", "Campinas", "SP");

        Cliente maria = new ClienteBuilder()
                .nome("Maria")
                .cpf(41526374855L)
                .endereco(endereco)
                .telefone(74635344855L)
                .listaDePedido(new ArrayList<>())
                .build();

        clienteDAO.cadastrar(maria);
        Mockito.verify(clienteDAO, Mockito.times(1)).cadastrar(Mockito.any(Cliente.class));

    }

    @Test
    public void deveriaRetornarUmCliente() {
        clienteDAO = Mockito.mock(ClienteDAO.class);

        Endereco endereco = new Endereco("Av. quinta", "424", "", "Santa Genebra", "Campinas", "SP");

        Cliente maria = new ClienteBuilder()
                .id()
                .nome("Maria")
                .cpf(41526374855L)
                .endereco(endereco)
                .telefone(74635344855L)
                .listaDePedido(new ArrayList<>())
                .build();

        Mockito.when(clienteDAO.buscaPorId(Mockito.any())).thenReturn(maria);

        Cliente retornoMaria = clienteDAO.buscaPorId(maria.getId());

        Assertions.assertEquals(maria, retornoMaria);

    }

    @Test
    public void deveriaListarTodosClientes() {
        clienteDAO = Mockito.mock(ClienteDAO.class);

        List<Cliente> clientesEsperados = clientesTeste();

        Mockito.when(clienteDAO.listaTodos()).thenReturn(clientesEsperados);

        List<Cliente> clientesRecuperados = clienteDAO.listaTodos();

        Assertions.assertEquals(clientesEsperados, clientesRecuperados);

    }

    private List<Cliente> clientesTeste() {
        List<Cliente> lista = new ArrayList<>();

        Endereco endereco = new Endereco("Av. quinta", "424", "", "Santa Genebra", "Campinas", "SP");

        lista.add(new ClienteBuilder()
                .id()
                .nome("Maria")
                .cpf(41533374855L)
                .endereco(endereco)
                .telefone(74635344855L)
                .listaDePedido(new ArrayList<>())
                .build());

        lista.add(new ClienteBuilder()
                .id()
                .nome("Pedro")
                .cpf(4152632324855L)
                .endereco(endereco)
                .telefone(74635344855L)
                .listaDePedido(new ArrayList<>())
                .build());

        lista.add(new ClienteBuilder()
                .id()
                .nome("José")
                .cpf(33326374855L)
                .endereco(endereco)
                .telefone(74635344855L)
                .listaDePedido(new ArrayList<>())
                .build());

        return lista;
    }


}
