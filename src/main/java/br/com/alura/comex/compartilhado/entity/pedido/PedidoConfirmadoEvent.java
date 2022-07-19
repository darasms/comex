package br.com.alura.comex.compartilhado.entity.pedido;

public interface PedidoConfirmadoEvent {

    void enviarEventoPedidoConfirmado(Pedido pedido);
}
