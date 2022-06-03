package br.com.alura.comex;

import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.StatusEnum;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        Categoria livro = new Categoria();
        livro.setNome("Teste");
        livro.setStatus(StatusEnum.ATIVA);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("comex");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(livro);
        em.getTransaction().commit();
        em.close();
    }
}
