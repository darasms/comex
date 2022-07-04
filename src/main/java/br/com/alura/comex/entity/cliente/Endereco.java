package br.com.alura.comex.entity.cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco {

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

}
