package br.com.alura.comex.entity.pedido;

import br.com.alura.comex.entity.cliente.Cliente;
import br.com.alura.comex.entity.enuns.TipoDesconto;
import br.com.alura.comex.entity.itemDePedido.ItemDePedido;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pedido {

    private Long id;

    private LocalDate data = LocalDate.now();

    private Cliente cliente;

    private List<ItemDePedido> itens = new ArrayList<>();

    private BigDecimal desconto = BigDecimal.ZERO;

    private TipoDesconto tipoDesconto = TipoDesconto.NENHUM;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemDePedido item) {
        item.setPedido(this);
        this.itens.add(item);
    }

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
