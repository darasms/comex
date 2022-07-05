package br.com.alura.comex.pedido.infra.ItemDePedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDePedidoDAO extends JpaRepository<ItemDePedidoEntity, Long> {
}
