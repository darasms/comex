package br.com.alura.comex;

import br.com.alura.comex.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Categoria informatica = new Categoria("INFOMATICA", StatusEnum.ATIVA);

        Cliente cliente = new Cliente("Maria", 413566673877L, 38245566L, "Av. quinta", "424", "", "Santa Genebra", "Campinas", "SP");

        Produto impressora = new Produto("Impressora", "", new BigDecimal(2), 2, informatica);

        ItemDePedido item = new ItemDePedido(new BigDecimal(3), 2, null, impressora, BigDecimal.ZERO, new TipoDesconto("FIDELIDADE"));

        List<ItemDePedido> listaPedido = new ArrayList<>();
        listaPedido.add(item);

        //Pedido novoPedido = new Pedido(cliente, listaPedido, BigDecimal.ZERO, new TipoDesconto("FIDELIDADE"));


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("comex");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();

    }
}
