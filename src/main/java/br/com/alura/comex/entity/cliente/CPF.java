package br.com.alura.comex.entity.cliente;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CPF {

    private final ValidadorCPF validadorCPF;
    private String numero;

    public CPF(ValidadorCPF validadorCPF, String numero) {
        this.validadorCPF = validadorCPF;
        this.numero = validadorCPF.validar(numero);
    }

}
