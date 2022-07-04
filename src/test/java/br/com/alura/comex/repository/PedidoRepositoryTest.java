package br.com.alura.comex.repository;

import br.com.alura.comex.infra.ItemDePedido.ItemDePedido;
import br.com.alura.comex.infra.categoria.CategoriaEntity;
import br.com.alura.comex.infra.cliente.ClienteEntity;
import br.com.alura.comex.infra.cliente.Endereco;
import br.com.alura.comex.infra.pedido.Pedido;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import br.com.alura.comex.model.*;
import br.com.alura.comex.entity.categoria.RelatorioPedidosPorCategoriaProjecao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PedidoRepositoryTest {
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
                .containsExactly(tuple(("INFORMÁTICA"), (10L), new BigDecimal("305.00")), tuple("FILMES", 3L, new BigDecimal("75.00")) );
    }

    private void persistirRegistrosTeste() {

        CategoriaEntity informatica = new CategoriaEntity("INFORMÁTICA");
        CategoriaEntity filmes = new CategoriaEntity("FILMES");

        ProdutoEntity mouse = new ProdutoEntity("Mouse", "Mouse", new BigDecimal("30.50"), 15, informatica);
        ProdutoEntity moana = new ProdutoEntity("Moana", "BlueRay",  new BigDecimal(25), 8, filmes);

        Endereco endereco = new Endereco("Rua da esquina", "366", "H22", "Santa Genebra", "Campinas", "SP");
        ClienteEntity kelvin = new ClienteEntity("Kelvin", 4156667228L, "198273666444", endereco);

        Pedido pedido1 = new Pedido(kelvin);
        Pedido pedido2 = new Pedido(kelvin);
        Pedido pedido3 = new Pedido(kelvin);

        kelvin.adicionarPedido(pedido1);
        kelvin.adicionarPedido(pedido2);
        kelvin.adicionarPedido(pedido3);

        ItemDePedido item1 = new ItemDePedido(7, mouse);
        ItemDePedido item3 = new ItemDePedido(3, mouse);
        ItemDePedido item2 = new ItemDePedido(3, moana);

        em.persist(informatica);
        em.persist(filmes);

        em.persist(mouse);
        em.persist(moana);

        em.persist(kelvin);


        em.persist(pedido1);
        em.persist(pedido2);
        em.persist(pedido3);

        pedido1.adicionarItem(item1);
        pedido2.adicionarItem(item2);
        pedido3.adicionarItem(item3);


    }


}