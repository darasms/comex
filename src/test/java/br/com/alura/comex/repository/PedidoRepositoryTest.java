package br.com.alura.comex.repository;

import br.com.alura.comex.model.projecao.RelatorioPedidosPorCategoriaProjecao;
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

        List<RelatorioPedidosPorCategoriaProjecao> resultado = repository.findPedidosPorCategoria();

        System.out.println(resultado.get(0).getNome());
        System.out.println(resultado.get(1).getNome());


        assertThat(resultado)
                .hasSize(2)
                .extracting(RelatorioPedidosPorCategoriaProjecao::getNome,
                        RelatorioPedidosPorCategoriaProjecao::getQuantidadeProdutos,
                        RelatorioPedidosPorCategoriaProjecao::getMontanteVendido)
                .containsExactly(tuple(("INFORMÁTICA"), (10L), new BigDecimal("305.00")), tuple("FILMES", 3L, new BigDecimal("75.00")) );
    }

}