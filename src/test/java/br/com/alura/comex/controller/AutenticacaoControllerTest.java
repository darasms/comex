package br.com.alura.comex.controller;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Endereco;
import br.com.alura.comex.model.Usuario;
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
import java.net.URI;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
class AutenticacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager testEntityManager;

    private Endereco DEFAULT_ENDERECO = new Endereco( "rua", "344", "complemento", "bairro", "cidade", "estado" );

    private Cliente cliente = new Cliente("aluno", 123456789L, "1982873847", DEFAULT_ENDERECO);

    @BeforeEach
    void setup(){

        testEntityManager.persist(cliente);
        Usuario aluno = new Usuario();
        aluno.setEmail("aluno@exemplo.com");
        aluno.setSenha("123456");
        aluno.setCliente(cliente);

        testEntityManager.persist(aluno);

        cliente.setUsuario(aluno);
    }

    @Test
    public void deveriaRetornarBadRequest() throws Exception {
        URI uri = new URI("/auth");

        String autenticacao = new JSONObject().put("email", "erro@exemplo.com")
                .put("senha", "123456").toString();

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri))
                .andExpect(status().isBadRequest());

    }
}