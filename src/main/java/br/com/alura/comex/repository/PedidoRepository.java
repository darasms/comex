package br.com.alura.comex.repository;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.projecao.RelatorioPedidosPorCategoriaProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT c.nome, item.quantidade AS quantidadeProdutos, (item.preco_unitario * item.quantidade) AS montanteVendido " +
            "FROM pedidos " +
            "JOIN itens_pedido item " +
            "JOIN produtos p " +
            "JOIN categorias c " +
            "WHERE pedidos.id = item.pedido_id AND item.produto_id = p.id AND p.categoria_id = c.id " +
            "GROUP BY item.id", nativeQuery = true)
    List<RelatorioPedidosPorCategoriaProjecao> findPedidosPorCategoria();

}
