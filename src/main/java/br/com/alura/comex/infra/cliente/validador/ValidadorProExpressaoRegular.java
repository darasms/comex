package br.com.alura.comex.infra.cliente.validador;

import br.com.alura.comex.entity.cliente.ValidadorCPF;

public class ValidadorProExpressaoRegular implements ValidadorCPF {

    @Override
    public String validar(String cpf) {
//        if (cpf == null ||
//                !cpf.matches(new String("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}"))) {
//            throw new IllegalArgumentException("CPF invalido!");
//        }
        return cpf;
    }
}
