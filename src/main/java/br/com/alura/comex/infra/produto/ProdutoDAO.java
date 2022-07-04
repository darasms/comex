package br.com.alura.comex.infra.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDAO extends JpaRepository<ProdutoEntity, Long> {
}
