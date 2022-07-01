package br.com.alura.comex.repository;

import br.com.alura.comex.infra.ItemDePedido.ItemDePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDePedidoRepository extends JpaRepository<ItemDePedido, Long> {
}
