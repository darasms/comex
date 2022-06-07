package br.com.alura.comex.dao;

import br.com.alura.comex.entity.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualiza(Produto produto) {

        Produto categoriaNovasInfos = this.buscarPorId(produto.getId());
        categoriaNovasInfos.setCategoria(produto.getCategoria());
        categoriaNovasInfos.setName(produto.getName());
        categoriaNovasInfos.setDescricao(produto.getDescricao());
        categoriaNovasInfos.setPrecoUnitario(produto.getPrecoUnitario());
        categoriaNovasInfos.setQuantidadeEstoque(produto.getQuantidadeEstoque());

        this.em.merge(categoriaNovasInfos);

    }

    public List<Produto> listaTodas() {
        return em.createQuery("FROM" + Produto.class.getName(), Produto.class).getResultList();
    }

    public List<Produto> listaIndisponível() {
        return em.createQuery("FROM" + Produto.class.getName() + "As a WHERE a.quantidade < 0", Produto.class).getResultList();
    }

    public Produto buscarPorId(Long id) {
        return this.em.find(Produto.class, id);
    }


}
