package br.com.alura.comex.entity.cliente;

public class Telefone {

    private final ValidadorTelefone validadorTelefone;
    private String ddd;
    private String numero;

    public Telefone(ValidadorTelefone validadorTelefone, String ddd, String numero) {
        this.validadorTelefone = validadorTelefone;
        this.ddd = validadorTelefone.validarDDD(ddd);
        this.numero = validadorTelefone.validarNumero(numero);
    }
}
