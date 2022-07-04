package br.com.alura.comex.adapter.dto.cliente;

import br.com.alura.comex.infra.cliente.ClienteEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter @Setter
public class ResumoClienteDto {
    private String nome;
    private String cpf;
    private String telefone;
    private String local;

    public ResumoClienteDto(ClienteEntity clienteEntity) {
        this.nome = clienteEntity.getNome();
        this.cpf = clienteEntity.getCpf();
        this.telefone = clienteEntity.getNumeroTelefone();
        this.local = clienteEntity.getEndereco().getCidade() + "/" + clienteEntity.getEndereco().getEstado().toUpperCase();
    }



    public static Page<ResumoClienteDto> converter(Page<ClienteEntity> clientes) {
        return clientes.map(ResumoClienteDto::new);
    }

}
