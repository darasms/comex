package br.com.alura.comex.entity.pedido;

import br.com.alura.comex.infra.cliente.ClienteEntity;
import br.com.alura.comex.infra.ItemDePedido.ItemDePedido;
import br.com.alura.comex.entity.enuns.TipoDesconto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido {

    private Long id;

    private LocalDate data = LocalDate.now();

    private ClienteEntity clienteEntity;

    private List<ItemDePedido> itens = new ArrayList<>();

    private BigDecimal desconto = BigDecimal.ZERO;

    private TipoDesconto tipoDesconto = TipoDesconto.NENHUM;

    public int getQuantidadeDeProdutos() {
        return this.itens.stream()
                .mapToInt(ItemDePedido::getQuantidade)
                .sum();
    }

    public BigDecimal getValorTotalPedido() {
        return this.itens.stream()
                .map(ItemDePedido::getValorTotalItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(this.getValorTotalDescontos());
    }

    public BigDecimal getValorTotalDescontos() {
        return this.itens.stream()
                .map(ItemDePedido::getDesconto)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(this.desconto);
    }


}
