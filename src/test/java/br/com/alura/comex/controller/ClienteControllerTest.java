//package br.com.alura.comex.controller;
//
//
//import br.com.alura.comex.model.Cliente;
//import br.com.alura.comex.model.Endereco;
//import br.com.alura.comex.repository.ClienteRepository;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@WebMvcTest(ClienteController.class)
//@ActiveProfiles("test")
//class ClienteControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ClienteRepository clienteRepository;
//
//
//    private Endereco DEFAULT_ENDERECO = new Endereco("Rua da esquina", "366", "H22", "Santa Genebra", "Campinas", "SP");
//
//
//    @WithMockUser("aluno@email.com")
//    @Test
//    public void deveriaRetornarTodosClientesOrdenadosPorNomeUtilizandoPaginacaoPadrao() throws Exception {
//
//        clienteRepository.save(new Cliente("Kelvin", 4156667228L, "198273666444", DEFAULT_ENDERECO));
//
//
//        MvcResult retorno = mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$content[0].nome").value("Amanda"))
//                .andExpect(jsonPath("$$content[1].nome").value("Kelvin")).andReturn();
//        System.out.println(">>>>>");
//    }
//
//    @Test
//    public void deveriaPersistirUmCliente() throws Exception {
//
//        String request = new JSONObject()
//                .put("nome", "Kelvin")
//                .put("cpf", 415627499)
//                .put("telefone", "985442236")
//                .put("rua", "Delegado Garcia")
//                .put("numero", "582")
//                .put("complemento", "Bloco G")
//                .put("bairro", "Outro universo")
//                .put("cidade", "Campinas")
//                .put("estado", "SP").toString();
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(request))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.nome").value("Kelvin"));
//
//    }
//
//    @Test
//    private void deveriaGerarErroDeValidaçãoDeCampos(){
//
//    }
//
//
//}