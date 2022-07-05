//package br.com.alura.comex.controller;
//
//import br.com.alura.comex.infra.cliente.ClienteEntity;
//import br.com.alura.comex.infra.cliente.EnderecoEntity;
//import br.com.alura.comex.infra.usuario.UsuarioEntity;
//import org.json.JSONObject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.transaction.Transactional;
//import java.net.URI;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureTestEntityManager
//@Transactional
//@ActiveProfiles("test")
//class AutenticacaoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    private EnderecoEntity DEFAULT_ENDERECOEntity = new EnderecoEntity( "rua", "344", "complemento", "bairro", "cidade", "estado" );
//
//    private ClienteEntity clienteEntity = new ClienteEntity("aluno", 123456789L, "1982873847", DEFAULT_ENDERECOEntity);
//
//    @BeforeEach
//    void setup(){
//
//        testEntityManager.persist(clienteEntity);
//        UsuarioEntity aluno = new UsuarioEntity();
//        aluno.setEmail("aluno@exemplo.com");
//        aluno.setSenha("$2a$10$JhY8lcscK7wotSZJCnNCL..ZmEq.R9TUGPo00Bai1qc4GkczudRTW");
//        aluno.setCliente(clienteEntity);
//
//        testEntityManager.persist(aluno);
//
//        clienteEntity.setUsuarioEntity(aluno);
//    }
//
//    @Test
//    public void deveriaRealizarAAltenticacao() throws Exception {
//        URI uri = new URI("/auth");
//
//        String autenticacao = new JSONObject().put("email", "aluno@exemplo.com")
//                .put("senha", "123456").toString();
//
//        mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post(uri)
//                        .content(autenticacao)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers
//                        .status()
//                        .is(200))
//                .andExpect(jsonPath("$.tipo").value("Bearer"));
//    }
//
//    @Test
//    public void deveriaRetornarBadRequest() throws Exception {
//        URI uri = new URI("/auth");
//
//        String autenticacao = new JSONObject().put("email", "erro@exemplo.com")
//                .put("senha", "123456").toString();
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post(uri)
//                        .content(autenticacao).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//
//    }
//}