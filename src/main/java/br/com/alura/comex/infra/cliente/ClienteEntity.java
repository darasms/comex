package br.com.alura.comex.infra.cliente;


import br.com.alura.comex.entity.cliente.Cliente;
import br.com.alura.comex.infra.usuario.Usuario;
import br.com.alura.comex.infra.pedido.Pedido;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @Builder
@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

//    @Embedded
//    private TelefoneEntity telefone;

    @Column(nullable = false)
    private String ddd;

    @Column(nullable = false)
    private String numeroTelefone;

    @Embedded
    private EnderecoEntity endereco;

    @OneToMany(mappedBy = "id")
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToOne(optional = false, mappedBy = "clienteEntity")
    private Usuario usuario;


    public static ClienteEntity converter(Cliente cliente) {
        return ClienteEntity.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf().getNumero())
                .endereco(EnderecoEntity.builder()
                        .cidade(cliente.getEndereco().getCidade())
                        .bairro(cliente.getEndereco().getBairro())
                        .estado(cliente.getEndereco().getEstado())
                        .rua(cliente.getEndereco().getRua())
                        .numero(cliente.getEndereco().getNumero())
                        .complemento(cliente.getEndereco().getComplemento())
                        .build())
                .ddd(cliente.getTelefone().getDdd())
                .numeroTelefone(cliente.getTelefone().getNumero())
                .build();
    }

    public void adicionarPedido(Pedido pedido){
        pedido.setCliente(this);
        this.pedidos.add(pedido);
    }

    public BigDecimal getMontanteGasto(){
        return pedidos.stream().map(Pedido::getValorTotalPedido).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Cliente paraCliente() {
        return null;
//        return Cliente.builder()
//                .cpf()
//                .nome()
//                .endereco()
//                .telefone()
//                .build();
    }
}
