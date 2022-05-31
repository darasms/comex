package br.com.alura.comex.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PedidoBuilderTest {
    private static final Logger LOG = LogManager.getLogger(PedidoBuilderTest.class);

    @Test
    @DisplayName("Test Data with builder in the plain class")
    public void plainClassWithBuilder() {
        int year = 2022;
        int month = 3;
        int day = 21;

        Pedido pedido = new PedidoBuilder().
                categoria("INFORMATICA").
                produto("IMPRESSORA").
                preco(new BigDecimal(200)).
                quantidade(1).
                data(LocalDate.of(year, month, day)).
                cliente("Aparecido").
                build();

        LOG.info(pedido);

        assertNotNull(pedido);
    }

}