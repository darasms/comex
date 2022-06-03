package br.com.alura.comex.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

    campos: id, nome, descricao, preco_unitario, quantidade_estoque, categoria_id
}
