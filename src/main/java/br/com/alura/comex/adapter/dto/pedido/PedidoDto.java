package br.com.alura.comex.adapter.dto.pedido;

import br.com.alura.comex.infra.pedido.PedidoEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoDto {

    private LocalDate data;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private int quantidadeProdutos;

    private Long idCliente;

    private String nomeCliente;

    public PedidoDto(PedidoEntity pedidoEntity) {
        this.data = pedidoEntity.getData();
        this.valorTotal = pedidoEntity.getValorTotalPedido();
        this.desconto = pedidoEntity.getValorTotalDescontos();
        this.quantidadeProdutos = pedidoEntity.getQuantidadeDeProdutos();
        this.idCliente = pedidoEntity.getCliente().getId();
        this.nomeCliente = pedidoEntity.getCliente().getNome();
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public static Page<PedidoDto> converter(Page<PedidoEntity> pedidosDb) {
        return pedidosDb.map(PedidoDto::new);
    }
}
