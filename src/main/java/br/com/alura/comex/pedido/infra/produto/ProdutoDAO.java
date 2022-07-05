package br.com.alura.comex.pedido.infra.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDAO extends JpaRepository<ProdutoEntity, Long> {
}
