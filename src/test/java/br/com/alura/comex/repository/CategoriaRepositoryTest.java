package br.com.alura.comex.repository;

import br.com.alura.comex.pedido.infra.categoria.CategoriaEntity;
import br.com.alura.comex.pedido.infra.categoria.CategoriaDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaDAO repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaRetornarListaDeCategorias(){

        CategoriaEntity categoria = new CategoriaEntity("FILMES");

        repository.save(categoria);

        Optional<CategoriaEntity> retorno = repository.findById(categoria.getId());

        assertThat(retorno).contains(categoria);

    }

}