package br.com.alura.comex.repository;

import br.com.alura.comex.infra.produto.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
