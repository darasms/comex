package br.com.alura.comex;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ProcessadorDeCsv {

    public List<Pedido> getPedidos(String caminho_arquivo ) throws FileNotFoundException {

        return new CsvToBeanBuilder<Pedido>(new FileReader(caminho_arquivo))
                .withType(Pedido.class)
                .withSeparator(',')
                .build()
                .parse();
    }

}
