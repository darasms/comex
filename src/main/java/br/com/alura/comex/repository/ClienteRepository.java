package br.com.alura.comex.repository;

import br.com.alura.comex.infra.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
