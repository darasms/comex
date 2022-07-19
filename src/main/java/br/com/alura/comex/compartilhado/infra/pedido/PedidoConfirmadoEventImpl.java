package br.com.alura.comex.compartilhado.infra.pedido;

import br.com.alura.comex.compartilhado.config.kafka.PedidoStreamConfig;
import br.com.alura.comex.compartilhado.entity.pedido.Pedido;
import br.com.alura.comex.compartilhado.entity.pedido.PedidoConfirmadoEvent;
import br.com.alura.comex.compartilhado.infra.pedido.dto.PedidoConfirmadoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PedidoConfirmadoEventImpl implements PedidoConfirmadoEvent {

    private final PedidoStreamConfig.PedidoConfirmadoSource pedidoConfirmadoSource;
    @Override
    public void enviarEventoPedidoConfirmado(Pedido pedido) {

        Message<?> pedidoConfirmadoEvent = MessageBuilder.withPayload(new PedidoConfirmadoDto(pedido)).build();
        pedidoConfirmadoSource.pedidosConfirmados().send(pedidoConfirmadoEvent);
    }
}
