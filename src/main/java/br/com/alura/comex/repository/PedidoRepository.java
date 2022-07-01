package br.com.alura.comex.repository;

import br.com.alura.comex.infra.pedido.Pedido;
import br.com.alura.comex.entity.categoria.RelatorioPedidosPorCategoriaProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT c.nome, SUM(item.quantidade) AS quantidadeProdutos, SUM((item.preco_unitario * item.quantidade)) AS montanteVendido " +
            "FROM itens_pedido item " +
            "JOIN produtos p " +
            "JOIN categorias c " +
            "WHERE item.produto_id = p.id AND p.categoria_id = c.id " +
            "GROUP BY item.produto_id, c.nome", nativeQuery = true)
    List<RelatorioPedidosPorCategoriaProjecao> findPedidosPorCategoria();

}
