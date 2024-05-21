package school.sptech.apimiseenplace.dto.personalizacao;

import lombok.Data;
import school.sptech.apimiseenplace.dto.produto_pedido.ProdutoPedidoListagemDTO;
import school.sptech.apimiseenplace.entity.Cobertura;
import school.sptech.apimiseenplace.entity.Massa;
import school.sptech.apimiseenplace.entity.Recheio;

import java.util.List;

@Data
public class PersonalizacaoListagemDto {

    private String tema;
    private Recheio recheio;
    private Massa massa;
    private Cobertura cobertura;
    private List<ProdutoPedidoListagemDTO> produtoPedidos;
}
