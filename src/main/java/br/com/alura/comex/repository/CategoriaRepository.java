package br.com.alura.comex.repository;

import br.com.alura.comex.infra.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
