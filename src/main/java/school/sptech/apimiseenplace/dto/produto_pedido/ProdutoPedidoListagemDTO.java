package school.sptech.apimiseenplace.dto.produto_pedido;

import lombok.Data;
import school.sptech.apimiseenplace.entity.Pedido;
import school.sptech.apimiseenplace.entity.Personalizacao;
import school.sptech.apimiseenplace.entity.Produto;

@Data
public class ProdutoPedidoListagemDTO {

    private Integer idProdutoPedido;
    private int qtProduto;
    private String observacoes;
    private Produto produto;
    private Personalizacao personalizacao;
    private Pedido pedido;
}
