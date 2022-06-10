package br.com.alura.comex.repository;

import br.com.alura.comex.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    Categoria findByNome(String nome);
}
