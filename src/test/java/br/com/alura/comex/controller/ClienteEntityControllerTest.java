package br.com.alura.comex.controller;


import br.com.alura.comex.infra.cliente.ClienteEntity;
import br.com.alura.comex.infra.cliente.Endereco;
import br.com.alura.comex.infra.cliente.ClienteDAO;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestEntityManager
@ActiveProfiles("test")
class ClienteEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteDAO clienteDAO;

    private Endereco DEFAULT_ENDERECO = new Endereco("Rua da esquina", "366", "H22", "Santa Genebra", "Campinas", "SP");


    @Test
    public void deveriaRetornarTodosClientesOrdenadosPorNomeUtilizandoPaginacaoPadrao() throws Exception {

        clienteDAO.save(new ClienteEntity("Kelvin", 4156667228L, "198273666444", DEFAULT_ENDERECO));
        clienteDAO.save(new ClienteEntity("Amanda", 4156667228L, "198273666444", DEFAULT_ENDERECO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].nome").value("Amanda"))
                .andExpect(jsonPath("$.content[1].nome").value("Kelvin"));

    }

    @Test
    public void deveriaPersistirUmCliente() throws Exception {

        String request = new JSONObject()
                .put("nome", "Kelvin")
                .put("cpf", 415627499)
                .put("telefone", "985442236")
                .put("rua", "Delegado Garcia")
                .put("numero", "582")
                .put("complemento", "Bloco G")
                .put("bairro", "Outro universo")
                .put("cidade", "Campinas")
                .put("estado", "SP").toString();


        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Kelvin"));

    }

    @Test
    public void deveriaGerarErroDeValidaçãoDeCampos() throws Exception {

        String request = new JSONObject()
                .put("nome", "Kelvin")
                .put("cpf", 415627499)
                .put("rua", "Delegado Garcia")
                .put("numero", "582")
                .put("complemento", "Bloco G")
                .put("bairro", "Outro universo")
                .put("cidade", "Campinas")
                .put("estado", "SP").toString();


        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());


    }


}