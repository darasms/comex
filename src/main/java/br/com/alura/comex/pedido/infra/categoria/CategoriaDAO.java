package br.com.alura.comex.pedido.infra.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDAO extends JpaRepository<CategoriaEntity, Long> {
}
