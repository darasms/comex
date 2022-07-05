package br.com.alura.comex.adapter.dto.pedido;

import br.com.alura.comex.entity.pedido.Pedido;
import br.com.alura.comex.infra.pedido.PedidoEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PedidoDto {

    private LocalDate data;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private int quantidadeProdutos;

    private Long idCliente;

    private String nomeCliente;

    public PedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotalPedido();
        this.desconto = pedido.getValorTotalDescontos();
        this.quantidadeProdutos = pedido.getQuantidadeDeProdutos();
        this.idCliente = pedido.getCliente().getId();
        this.nomeCliente = pedido.getCliente().getNome();
    }

    public static Page<PedidoDto> converter(Page<Pedido> pedidosDb) {
        return pedidosDb.map(PedidoDto::new);
    }
}
