package br.com.alura.comex.controller;


import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

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
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));

    }

    private JSONObject criarObjetoJson() throws JSONException {

        Categoria categoria = categoriaRepository.save(new Categoria("Eletrônicos"));

        return new JSONObject()
                .put("nome","Tela")
                .put("descricao", "4K com AI")
                .put("precoUnitario",5000.00)
                .put("quantidadeEstoque", 2)
                .put("categoria", categoria.getId());
    }

}