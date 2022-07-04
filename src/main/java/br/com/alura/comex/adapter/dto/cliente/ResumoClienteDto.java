package br.com.alura.comex.adapter.dto.cliente;

import br.com.alura.comex.infra.cliente.ClienteEntity;
import org.springframework.data.domain.Page;

public class ResumoClienteDto {
    private String nome;
    private Long cpf;
    private String telefone;
    private String local;

    public ResumoClienteDto(ClienteEntity clienteEntity) {
        this.nome = clienteEntity.getNome();
        this.cpf = clienteEntity.getCpf();
        this.telefone = clienteEntity.getTelefone();
        this.local = clienteEntity.getEndereco().getCidade() + "/" + clienteEntity.getEndereco().getEstado().toUpperCase();
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

    public String getLocal() {
        return local;
    }

    public static Page<ResumoClienteDto> converter(Page<ClienteEntity> clientes) {
        return clientes.map(ResumoClienteDto::new);
    }

}
