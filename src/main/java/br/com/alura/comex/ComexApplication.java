package br.com.alura.comex;

import br.com.alura.comex.entity.projecao.RelatorioPedidosPorCategoriaProjecao;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ComexApplication {

    public static void main(String[] args) {

        SpringApplication.run(ComexApplication.class, args);

    }
}
