package br.com.alura.comex.controller;


import br.com.alura.comex.adapter.form.ItemDePedidoForm;
import br.com.alura.comex.adapter.form.PedidoFrom;
import br.com.alura.comex.infra.ItemDePedido.ItemDePedido;
import br.com.alura.comex.infra.categoria.CategoriaEntity;
import br.com.alura.comex.infra.cliente.ClienteEntity;
import br.com.alura.comex.infra.cliente.EnderecoEntity;
import br.com.alura.comex.infra.pedido.Pedido;
import br.com.alura.comex.infra.produto.ProdutoEntity;
import br.com.alura.comex.model.*;
import br.com.alura.comex.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestEntityManager
@ActiveProfiles("test")
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager em;


    @Autowired
    private PedidoRepository pedidoRepository;

    private Pedido DEFAULT_PEDIDO;

    private EnderecoEntity DEFAULT_ENDERECOEntity = new EnderecoEntity("Rua da esquina", "366", "H22", "Santa Genebra", "Campinas", "SP");

    @Mock
    private ItemDePedidoForm itemDePedidoForm;

    @Mock
    private PedidoFrom pedido;

    @BeforeEach
    public void setup(){

        ClienteEntity kelvin = new ClienteEntity("Kelvin", 4156667228L, "198273666444", DEFAULT_ENDERECOEntity);
        DEFAULT_PEDIDO = new Pedido(kelvin);
        pedidoRepository.save(DEFAULT_PEDIDO);
    }

    @Test
    public void deveriaListarPedidos() throws Exception {

        URI uri = new URI("/api/pedidos");

        persistirRegistrosTeste();

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNotEmpty())
                .andExpect(jsonPath("$.content[0].data").value(LocalDate.now().toString()));
    }


    @Test
    public void deveriaListarDetalheDeUmPedido() throws Exception {
        URI uri = new URI("/api/pedidos/" + DEFAULT_PEDIDO.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCliente").value(DEFAULT_PEDIDO.getCliente().getId()))
                .andExpect(jsonPath("$.nomeCliente").value(DEFAULT_PEDIDO.getCliente().getNome()))
                .andExpect(jsonPath("$.itens").isEmpty())
                .andExpect(jsonPath("$.data").value(LocalDate.now().toString()));


    }

    @Test
    public void deveriaRetornarNotFoundParaIdInvalido() throws Exception {

        URI uri = new URI("/api/pedidos/99" );

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isNotFound());
    }


    private void persistirRegistrosTeste() {

        CategoriaEntity informatica = new CategoriaEntity("INFORMÁTICA");
        CategoriaEntity filmes = new CategoriaEntity("FILMES");

        ProdutoEntity mouse = new ProdutoEntity("Mouse", "Mouse", new BigDecimal("30.50"), 15, informatica);
        ProdutoEntity moana = new ProdutoEntity("Moana", "BlueRay",  new BigDecimal(25), 8, filmes);

        ClienteEntity kelvin = new ClienteEntity("Kelvin", 4156667228L, "198273666444", DEFAULT_ENDERECOEntity);

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