package br.com.alura.comex.entity.Cliente;

public interface ValidadorTelefone {

    String validarDDD(String ddd);

    String validarNumero(String numero);
}
