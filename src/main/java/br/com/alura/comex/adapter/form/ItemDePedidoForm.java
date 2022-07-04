//package br.com.alura.comex.adapter.form;
//
//import br.com.alura.comex.infra.ItemDePedido.ItemDePedido;
//import br.com.alura.comex.infra.produto.ProdutoEntity;
//
//import javax.validation.Valid;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import java.util.Optional;
//
//public class ItemDePedidoForm {
//
//    @NotNull
//    @Min(0)
//    @Valid
//    private Long idProduto;
//
//    @Min(1)
//    private int quantidadeProduto;
//
//    public ItemDePedidoForm() {
//    }
//
//    private ProdutoEntity verificarProduto(ProdutoRepository produtoRepository){
//        Optional<ProdutoEntity> produto = produtoRepository.findById(this.idProduto);
//
//        if (produto.get().getQuantidadeEstoque() < this.quantidadeProduto){
//            throw new RuntimeException("Sem produto em estoque");
//        }
//
//        produto.get().setQuantidadeEstoque(
//                produto.get().getQuantidadeEstoque() - this.quantidadeProduto
//        );
//
//        return produto.get();
//    }
//
//    public ItemDePedido converter(ProdutoRepository produtoRepository){
//        return new ItemDePedido(this.quantidadeProduto, verificarProduto(produtoRepository));
//    }
//
//
//    public Long getIdProduto() {
//        return idProduto;
//    }
//
//    public int getQuantidadeProduto() {
//        return quantidadeProduto;
//    }
//
//    @Override
//    public String toString() {
//        return "ItemDePedidoForm{" +
//                "idProduto=" + idProduto +
//                ", quantidadeProduto=" + quantidadeProduto +
//                '}';
//    }
//}
