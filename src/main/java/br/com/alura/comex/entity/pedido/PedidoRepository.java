package br.com.alura.comex.entity.pedido;

import br.com.alura.comex.entity.categoria.Categoria;
import br.com.alura.comex.entity.categoria.RelatorioPedidosPorCategoriaProjecao;

import java.util.List;

public interface PedidoRepository {

    List<Pedido> listarTodosPedidos();

    Pedido buscarPedidoPorCodIdentificador(Long codigoIdentificador);

    void cadastrarPedido(Pedido pedido);

    List<RelatorioPedidosPorCategoriaProjecao> buscarPedidosPorCategoria(Categoria categoria);

}
