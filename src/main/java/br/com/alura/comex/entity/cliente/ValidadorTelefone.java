package br.com.alura.comex.entity.cliente;

public interface ValidadorTelefone {

    String validarDDD(String ddd);

    String validarNumero(String numero);
}
