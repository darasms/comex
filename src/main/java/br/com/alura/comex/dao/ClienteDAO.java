package br.com.alura.comex.dao;

import br.com.alura.comex.entity.Cliente;

import javax.persistence.EntityManager;
import java.util.List;


public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        if (em == null ) throw new RuntimeException("EntityManager não pode ser nula");
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void atualiza(Cliente cliente) {

        Cliente clienteNovasInfos = this.buscaPorId(cliente.getId());
        clienteNovasInfos.setCpf(cliente.getCpf());
        clienteNovasInfos.setNome(cliente.getNome());
        clienteNovasInfos.setTelefone(cliente.getTelefone());
        clienteNovasInfos.setEndereco(cliente.getEndereco());
        clienteNovasInfos.setListaDePedido(cliente.getListaDePedido());

        this.em.merge(clienteNovasInfos);

    }

    public void remove(Cliente cliente) {
        this.em.remove(cliente);
    }

    public Cliente buscaPorId(Long id) {
       return this.em.find(Cliente.class, id);
    }

    public List<Cliente> listaTodos() {
        return em.createQuery("FROM" + Cliente.class.getName(), Cliente.class).getResultList();
    }
}
