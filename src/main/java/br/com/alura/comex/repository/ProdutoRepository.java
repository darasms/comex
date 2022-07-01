package br.com.alura.comex.repository;

import br.com.alura.comex.infra.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
