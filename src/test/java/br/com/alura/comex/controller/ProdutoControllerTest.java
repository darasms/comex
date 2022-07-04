package br.com.alura.comex.controller;


import br.com.alura.comex.infra.categoria.CategoriaEntity;
import br.com.alura.comex.infra.produto.ProdutoEntity;
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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URI;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProdutoRepository produtoRepository;

    private CategoriaEntity INFORMATICA;

    private ProdutoEntity DEFAULT_MOUSE;

    @BeforeEach
    void setup() {
        INFORMATICA = new CategoriaEntity("INFORMATICA");

        DEFAULT_MOUSE = new ProdutoEntity("mouse", "Dell", new BigDecimal("25.4"), 3, INFORMATICA );

        entityManager.persist(INFORMATICA);

        entityManager.persist(DEFAULT_MOUSE);
    }


    @Test
    public void deveriaListar2ProdutosOrdenadosPorNome() throws Exception {
        URI uri = new URI("/api/produtos");

        ProdutoEntity teclado = new ProdutoEntity("teclado", "Dell", new BigDecimal("45.90"), 10, INFORMATICA );
        entityManager.persist(teclado);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].nome", is("mouse")))
                .andExpect(jsonPath("$.content[1].nome", is("teclado")));

    }
    @Test
    void deveriaRetornarInformacoesMouse() throws Exception {
        URI uri = new URI("/api/produtos/" + DEFAULT_MOUSE.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(status().isOk())
                .andDo(log())
                .andExpect(jsonPath("$.id", equalTo(DEFAULT_MOUSE.getId().intValue())))
                .andExpect(jsonPath("$.nome", equalTo(DEFAULT_MOUSE.getName())))
                .andExpect(jsonPath("$.descricao", equalTo(DEFAULT_MOUSE.getDescricao())))
                .andExpect(jsonPath("$.quantidadeEstoque", equalTo(DEFAULT_MOUSE.getQuantidadeEstoque())))
                .andExpect(jsonPath("$.categoria", equalTo(DEFAULT_MOUSE.getCategoria().getNome())));
    }



    @Test
    public void deveriaCadastrarProduto() throws Exception {
        URI uri = new URI("/api/produtos");

        JSONObject json = criarObjetoJson();
        String request = json.toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(201));

    }

    @Test
    public void deveriaAlterarQuantidadeEmEstoqueDoProduto() throws Exception {

        URI uri = new URI("/api/produtos/" + DEFAULT_MOUSE.getId() );

        String request = criarObjetoProdutoJsonAlterado(DEFAULT_MOUSE).toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(200));

    }

    @Test
    public void deveriaRetornar404ParaProdutoNaoEncontradoParaAlteração() throws Exception {

        URI uri = new URI("/api/produtos/1010");

        String request = criarObjetoProdutoJsonAlterado(DEFAULT_MOUSE).toString();

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(404));

    }

    @Test
    public void deveriaRetornar400ParaAlteracaoComBodyVazio() throws Exception {

        URI uri = new URI("/api/produtos/" + DEFAULT_MOUSE.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .is(400));

    }

    @Test
    public void deveriaDeletarProduto() throws Exception {

        URI uri = new URI("/api/produtos/" + DEFAULT_MOUSE.getId());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete( uri)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }


    private JSONObject criarObjetoProdutoJsonAlterado(ProdutoEntity produtoEntity) throws JSONException {

        return new JSONObject()
                .put("nome", produtoEntity.getName())
                .put("descricao", produtoEntity.getDescricao())
                .put("precoUnitario", produtoEntity.getPrecoUnitario())
                .put("quantidadeEstoque", 10)
                .put("categoria", INFORMATICA.getId());
    }

    private JSONObject criarObjetoJson() throws JSONException {

        return new JSONObject()
                .put("nome","Tela")
                .put("descricao", "4K com AI")
                .put("precoUnitario",5000.00)
                .put("quantidadeEstoque", 2)
                .put("categoria", INFORMATICA.getId());
    }


}