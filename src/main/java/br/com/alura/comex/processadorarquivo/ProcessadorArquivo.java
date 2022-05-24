package br.com.alura.comex.processadorarquivo;

import br.com.alura.comex.Pedido;

import java.io.IOException;
import java.util.List;

public interface ProcessadorArquivo {
    List<Pedido> getPedidos(String caminho_arquivo) throws IOException;
}
