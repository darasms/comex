package br.com.alura.comex.entity.Cliente;


public class CPF {

    private final ValidadorCPF validadorCPF;
    private String numero;

    public CPF(ValidadorCPF validadorCPF, String numero) {
        this.validadorCPF = validadorCPF;
        this.numero = validadorCPF.validar(numero);
    }

}
