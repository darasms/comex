package br.com.alura.comex;

import br.com.alura.comex.dao.*;
import br.com.alura.comex.entity.*;
import br.com.alura.comex.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Categoria informatica = new Categoria("INFORMATICA", StatusEnum.ATIVA);

        Cliente cliente = new Cliente("Bela", 413566673877L, 38245566L, "Av. quinta", "424", "", "Santa Genebra", "Campinas", "SP");

        Produto impressora = new Produto("Mouse", "", new BigDecimal(2), 2, informatica);

        TipoDesconto fidelidade = new TipoDesconto("FIDELIDADE");

        ItemDePedido item = new ItemDePedido(new BigDecimal(3), 2, null, impressora, BigDecimal.ZERO, fidelidade);

        List<ItemDePedido> listaPedido = new ArrayList<>();
        listaPedido.add(item);

        List<TipoDesconto> listaDesconto = new ArrayList<>();
        listaDesconto.add(fidelidade);

        Pedido novoPedido = new Pedido(cliente, listaPedido, BigDecimal.ZERO, listaDesconto);

        EntityManager em = JPAUtil.getEntityManager();

        ClienteDAO clienteDAO = new ClienteDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        TipoDescontoDAO tipoDescontoDAO = new TipoDescontoDAO(em);
        ItemDePedidoDAO itemDePedidoDAO = new ItemDePedidoDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);



        em.getTransaction().begin();

        clienteDAO.cadastrar(cliente);
        categoriaDAO.cadastrar(informatica);
        produtoDAO.cadastrar(impressora);
        tipoDescontoDAO.cadastrar(fidelidade);
        itemDePedidoDAO.cadastrar(item);
        pedidoDAO.cadastrar(novoPedido);

        em.getTransaction().commit();
        em.close();

    }
}
