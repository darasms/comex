package br.com.alura.comex.compartilhado.config.kafka;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@EnableBinding(PedidoStreamConfig.PedidoConfirmadoSource.class)
@Configuration
public class PedidoStreamConfig {


    public interface PedidoConfirmadoSource {

        @Output
        MessageChannel pedidosConfirmados();
    }
}
