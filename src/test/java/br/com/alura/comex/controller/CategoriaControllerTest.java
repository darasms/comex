package br.com.alura.comex.controller;

import br.com.alura.comex.model.*;
import br.com.alura.comex.model.projecao.RelatorioPedidosPorCategoriaProjecao;
import br.com.alura.comex.repository.PedidoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoriaControllerTest {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaRetornarUmRegistroParaCadaCategoriaComDescontoQuantidadeEmUmDosRegistros() {
        persistirRegistrosTeste();

        List<RelatorioPedidosPorCategoriaProjecao> resultado = repository.findPedidosPorCategoria();

        assertThat(resultado)
                .hasSize(2)
                .extracting(RelatorioPedidosPorCategoriaProjecao::getNome,
                        RelatorioPedidosPorCategoriaProjecao::getQuantidadeProdutos,
                        RelatorioPedidosPorCategoriaProjecao::getMontanteVendido)
                .containsExactly(
                        tuple("INFORMÁTICA", 14, new BigDecimal("393.45"),
                                "FILMES", 3, new BigDecimal("75.00")));
    }

    private void persistirRegistrosTeste() {

        Categoria informatica = new Categoria("INFORMÁTICA");
        Categoria filmes = new Categoria("FILMES");

        Produto mouse = new Produto("Mouse", "Mouse", new BigDecimal("30.50"), 15, informatica);
        Produto moana = new Produto("Moana", "BlueRay",  new BigDecimal(25), 8, filmes);

        Endereco endereco = new Endereco("Rua da esquina", "366", "H22", "Santa Genebra", "Campinas", "SP");
        Cliente kelvin = new Cliente("Kelvin", 4156667228L, "198273666444", endereco);

        ItemDePedido item1 = new ItemDePedido(7, mouse);

        ItemDePedido item2 = new ItemDePedido(4, mouse);

        ItemDePedido item3 = new ItemDePedido(3, moana );

        List<ItemDePedido> listaDeItens1 = List.of(item1, item2);
        List<ItemDePedido> listaDeItens2 = List.of(item3);

        Pedido pedido1 = new Pedido(kelvin);
        listaDeItens1.forEach(pedido1::adicionarItem);

        Pedido pedido2 = new Pedido(kelvin);
        listaDeItens2.forEach(pedido2::adicionarItem);

        em.persist(informatica);
        em.persist(filmes);

        em.persist(mouse);
        em.persist(moana);

        em.persist(kelvin);

        em.persist(pedido1);
        em.persist(pedido2);

        em.persist(item1);
        em.persist(item2);

    }


}