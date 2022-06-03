package br.com.alura.comex.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

    campos: id, nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado
}
