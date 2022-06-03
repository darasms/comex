package br.com.alura.comex.dao;

import br.com.alura.comex.entity.Cliente;

import javax.persistence.EntityManager;

public class ClienteDAO {
    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }
}
