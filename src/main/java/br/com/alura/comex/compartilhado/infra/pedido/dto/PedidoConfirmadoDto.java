package br.com.alura.comex.compartilhado.infra.pedido.dto;

import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PedidoConfirmadoDto {

    private Long id;
    private LocalDate data;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private int quantidadeProdutos;

    private Long idCliente;

    private String nomeCliente;

    private List<ItemDePedidoConfirmadoDto> itens;

    public PedidoConfirmadoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotalPedido();
        this.desconto = pedido.getDesconto();
        this.quantidadeProdutos = pedido.getQuantidadeDeProdutos();
        this.idCliente = pedido.getCliente().getId();
        this.nomeCliente = pedido.getCliente().getNome();
        this.itens = pedido.getItens().stream().map(ItemDePedidoConfirmadoDto::new).collect(Collectors.toList());

    }
}
