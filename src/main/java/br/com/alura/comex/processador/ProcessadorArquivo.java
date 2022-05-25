package br.com.alura.comex.processador;

import br.com.alura.comex.model.Pedido;

import java.io.IOException;
import java.util.List;

public interface ProcessadorArquivo {
    List<Pedido> getPedidos(String caminho_arquivo) throws IOException;
}
