package br.com.alura.comex;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProcessadorJSON implements ProcessadorArquivo{

    public List<Pedido> getPedidos(String caminho_arquivo) throws IOException {
        return new ObjectMapper().readValue(new File(caminho_arquivo), new TypeReference<>() {});
    }
}
