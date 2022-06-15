package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.ProdutoRepository;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class PedidoFrom {

    @NotNull
    @Min(0)
    @Valid
    private Long idCliente;

    @NotNull
    private List<ItemDePedidoForm> itens;


    public Pedido converter(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
         Optional<Cliente> cliente = clienteRepository.findById(this.idCliente);

         this.itens.stream().forEach(item -> {
             item.converter(produtoRepository);
         });


        return new Pedido();
    }
}
