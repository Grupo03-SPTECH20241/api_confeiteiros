package school.sptech.apimiseenplace.dto.recheio;

import lombok.Data;
import org.springframework.boot.context.properties.bind.Nested;
import school.sptech.apimiseenplace.dto.cobertura.CoberturaSemPersonalizacaoDto;
import school.sptech.apimiseenplace.dto.massa.MassaSemPersonalizacaoDto;
import school.sptech.apimiseenplace.dto.personalizacao.PersonalizacaoListagemDto;
import school.sptech.apimiseenplace.dto.produto_pedido.ProdutoPedidoListagemDTO;
import school.sptech.apimiseenplace.entity.Cobertura;
import school.sptech.apimiseenplace.entity.Massa;
import school.sptech.apimiseenplace.entity.Recheio;

import java.util.List;

@Data
public class RecheioListagemDto {

    private String nome;
    private Double preco;
    private List<PersonalizacaoListagem> personalizacoes;

    @Data
    public static class PersonalizacaoListagem {
        private Integer id;
        private String tema;
        private RecheioSemPersonalizacaoDto recheio;
        private MassaSemPersonalizacaoDto massa;
        private CoberturaSemPersonalizacaoDto cobertura;
    }


}
