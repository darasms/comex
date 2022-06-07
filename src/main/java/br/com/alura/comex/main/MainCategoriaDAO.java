package br.com.alura.comex.main;

import br.com.alura.comex.dao.*;
import br.com.alura.comex.entity.Categoria;
import br.com.alura.comex.entity.StatusCategoria;
import br.com.alura.comex.entity.builder.CategoriaBuilder;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class MainCategoriaDAO {

    public static void main(String[] args){

        Categoria auto = new CategoriaBuilder().id().nome("AUTO").produtos(new ArrayList<>()).status(StatusCategoria.ATIVA).build();
        Categoria tech = new CategoriaBuilder().id().nome("TECNOLOGIA").produtos(new ArrayList<>()).status(StatusCategoria.ATIVA).build();
        Categoria livros = new CategoriaBuilder().id().nome("LIVROS").produtos(new ArrayList<>()).status(StatusCategoria.ATIVA).build();


        EntityManager em = JPAUtil.getEntityManager();

        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(auto);
        categoriaDAO.cadastrar(tech);
        categoriaDAO.cadastrar(livros);

        em.getTransaction().commit();
        em.close();

    }
}
