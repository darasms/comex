package br.com.alura.comex.adapter.dto.pedido;

import br.com.alura.comex.infra.pedido.PedidoEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesPedidoDto {

    private LocalDate data;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private int quantidadeProdutos;

    private Long idCliente;

    private String NomeCliente;

    private List<ItemDePedidoDto> itens;

    public DetalhesPedidoDto(PedidoEntity pedidoEntity) {
        this.data = pedidoEntity.getData();
        this.valorTotal = pedidoEntity.getValorTotalPedido();
        this.desconto = pedidoEntity.getDesconto();
        this.quantidadeProdutos = pedidoEntity.getQuantidadeDeProdutos();
        this.idCliente = pedidoEntity.getCliente().getId();
        NomeCliente = pedidoEntity.getCliente().getNome();
        this.itens = pedidoEntity.getItens().stream().map(ItemDePedidoDto::new).collect(Collectors.toList());
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
        return NomeCliente;
    }

    public List<ItemDePedidoDto> getItens() {
        return itens;
    }
}
