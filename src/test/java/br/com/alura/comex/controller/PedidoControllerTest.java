package br.com.alura.comex.controller;


import br.com.alura.comex.model.*;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private Endereco DEFAULT_ENDERECO = new Endereco("Rua da esquina", "366", "H22", "Santa Genebra", "Campinas", "SP");

    @BeforeEach
    public void setup(){

        Cliente kelvin = new Cliente("Kelvin", 4156667228L, "198273666444", DEFAULT_ENDERECO);
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
                .andExpect(jsonPath("$.content[0].data").value("2022-06-29"));
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
                .andExpect(jsonPath("$.data").value("2022-06-29"));


    }

    @Test
    public void deveriaRetornarNotFoundParaIdInvalido() throws Exception {

        URI uri = new URI("/api/pedidos/99" );

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isNotFound());
    }


//    @Test
//    public void deveriaCadastrarUmNovoPedido() throws Exception {
//        URI uri = new URI("/api/pedidos" );
//
//        String requestBody = criarUmNovoPedido().toString();
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post(uri)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.idCliente").value(2))
//                .andExpect(jsonPath("$.nomeCliente").value("Kelvin"))
//                .andExpect(jsonPath("$.itens").isEmpty())
//                .andExpect(jsonPath("$.data").value("2022-06-29"));
//
//    }

    private JSONObject criarUmNovoPedido() throws JSONException {

        Categoria informatica = new Categoria("INFORMÁTICA");
        Categoria filmes = new Categoria("FILMES");

        Produto mouse = new Produto("Mouse", "Mouse", new BigDecimal("30.50"), 15, informatica);
        Produto moana = new Produto("Moana", "BlueRay",  new BigDecimal(25), 8, filmes);

        Cliente kelvin = new Cliente("Kelvin", 4156667228L, "198273666444", DEFAULT_ENDERECO);

        em.persist(informatica);
        em.persist(filmes);

        em.persist(mouse);
        em.persist(moana);

        em.persist(kelvin);

  //      return new JSONObject().put("idCliente", kelvin.getId()).put("itens", List.of(new JSONObject().put("idProduto", moana.getId()).put("quantidadeProduto", 12), new JSONObject().put("idProduto", mouse.getId()).put("quantidadeProduto", 12)));
        return new JSONObject().put("itens", new ArrayList<>()).put("idCliente", kelvin.getId());

    }

    private void persistirRegistrosTeste() {

        Categoria informatica = new Categoria("INFORMÁTICA");
        Categoria filmes = new Categoria("FILMES");

        Produto mouse = new Produto("Mouse", "Mouse", new BigDecimal("30.50"), 15, informatica);
        Produto moana = new Produto("Moana", "BlueRay",  new BigDecimal(25), 8, filmes);

        Cliente kelvin = new Cliente("Kelvin", 4156667228L, "198273666444", DEFAULT_ENDERECO);

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