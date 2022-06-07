package br.com.alura.comex.main;

import br.com.alura.comex.dao.ClienteDAO;
import br.com.alura.comex.entity.Cliente;
import br.com.alura.comex.entity.Endereco;
import br.com.alura.comex.entity.builder.ClienteBuilder;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MainClienteDAO {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        ClienteDAO clienteDAO = new ClienteDAO(em);

        Endereco endereco = new Endereco("Av. quinta", "424", "", "Santa Genebra", "Campinas", "SP");

        /*List<Cliente> lista = new ArrayList<>();

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
*/

        Cliente cliente = new ClienteBuilder()
                .id()
                .nome("Maria")
                .cpf(41533374855L)
                .endereco(endereco)
                .telefone(74635344855L)
                .listaDePedido(new ArrayList<>())
                .build();

        Cliente arthur = new ClienteBuilder()
                .id()
                .nome("Arthur")
                .cpf(41533374855L)
                .endereco(endereco)
                .telefone(74635344855L)
                .listaDePedido(new ArrayList<>())
                .build();

        Cliente joao = new ClienteBuilder()
                .id()
                .nome("João")
                .cpf(41533374855L)
                .endereco(endereco)
                .telefone(74635344855L)
                .listaDePedido(new ArrayList<>())
                .build();

        em.getTransaction().begin();

        clienteDAO.cadastrar(cliente);
        clienteDAO.cadastrar(arthur);
        clienteDAO.cadastrar(joao);

        List<Cliente> listaArthur = clienteDAO.buscaPorNome("Arthur");

        em.getTransaction().commit();
        em.close();

        listaArthur.stream().forEach(System.out::println);
    }



}
