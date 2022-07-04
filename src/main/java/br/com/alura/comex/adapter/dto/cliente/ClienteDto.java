package br.com.alura.comex.adapter.dto.cliente;

import br.com.alura.comex.infra.cliente.ClienteEntity;

public class ClienteDto {

    private String nome;

    private Long cpf;

    private String telefone;

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    public ClienteDto(ClienteEntity clienteEntity) {
        this.nome = clienteEntity.getNome();
        this.cpf = clienteEntity.getCpf();
        this.telefone = clienteEntity.getTelefone();
        this.rua = clienteEntity.getEndereco().getRua();
        this.numero = clienteEntity.getEndereco().getNumero();
        this.complemento = clienteEntity.getEndereco().getComplemento();
        this.bairro = clienteEntity.getEndereco().getBairro();
        this.cidade = clienteEntity.getEndereco().getCidade();
        this.estado = clienteEntity.getEndereco().getEstado();
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
