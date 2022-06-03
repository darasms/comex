package br.com.alura.comex.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemDePedido {

    campos: id, preco_unitario, quantidade, pedido_id, produto_id, desconto, tipo_desconto (QUANTIDADE, PROMOCAO ou NENHUM)
}
