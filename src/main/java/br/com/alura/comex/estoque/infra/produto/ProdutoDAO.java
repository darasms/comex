package br.com.alura.comex.estoque.infra.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDAO extends JpaRepository<ProdutoEntity, Long> {
}
