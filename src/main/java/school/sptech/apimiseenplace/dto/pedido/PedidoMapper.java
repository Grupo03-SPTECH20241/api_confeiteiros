package school.sptech.apimiseenplace.dto.pedido;

import school.sptech.apimiseenplace.entity.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoMapper {
    public static Pedido toEntity(PedidoCriacaoDTO pedidoCriacaoDTO){
        if (pedidoCriacaoDTO == null) return null;
        Pedido entity = new Pedido();
        entity.setCliente(pedidoCriacaoDTO.getCliente());
        entity.setDataEntrega(pedidoCriacaoDTO.getDataEntrega());
        return entity;
    }

    public static PedidoListagemDTO toDto(Pedido pedido){
        if (pedido == null) return null;
        PedidoListagemDTO pedidoListagemDto = new PedidoListagemDTO();
        pedidoListagemDto.setId(pedido.getId());
        pedidoListagemDto.setCliente(pedido.getCliente());
        pedidoListagemDto.setDataEntrega(pedido.getDataEntrega());
        //pedidoListagemDto.setValorTotal(pedidoListagemDto.getValorTotal());

        if(pedido.getProdutos() != null) {
            List<PedidoProdutoListagemDTO> produtoListagemDTOS =
                    pedido.getProdutos().stream().map(produto -> {
                        PedidoProdutoListagemDTO dtoProduto = new PedidoProdutoListagemDTO();
                        dtoProduto.setId(produto.getId());
                        dtoProduto.setNome(produto.getNome());
                        dtoProduto.setPreco(produto.getPreco());
                        dtoProduto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
                        return dtoProduto;
                    }).toList();
            pedidoListagemDto.setProdutos(produtoListagemDTOS);
        }

        return pedidoListagemDto;
    }

    public static List<PedidoListagemDTO> toDto(List<Pedido> entities){
        if (entities.isEmpty()) return null;
        List<PedidoListagemDTO> listagemDTOS = new ArrayList<>();

        for (Pedido entity : entities){
            listagemDTOS.add(PedidoMapper.toDto(entity));
        }
        return listagemDTOS;
    }
}
