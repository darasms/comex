package br.com.alura.comex.usuario.adapter.dto.cliente;

import br.com.alura.comex.usuario.entity.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter @Setter
public class ResumoClienteDto {
    private String nome;
    private String cpf;
    private String telefone;
    private String local;

    public ResumoClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf().getNumero();
        this.telefone = cliente.getTelefone().telefoneCompleto();
        this.local = cliente.getEndereco().getCidade() + "/" + cliente.getEndereco().getEstado().toUpperCase();
    }



    public static Page<ResumoClienteDto> converter(Page<Cliente> clientes) {
        return clientes.map(ResumoClienteDto::new);
    }

}
