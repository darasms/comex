package br.com.alura.comex.usuario.entity.cliente;

public interface ValidadorTelefone {

    String validarDDD(String ddd);

    String validarNumero(String numero);
}
