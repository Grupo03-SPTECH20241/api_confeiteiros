package school.sptech.apimiseenplace.dto.pedidofesta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.web.client.RestTemplate;
import school.sptech.apimiseenplace.dto.produto.ProdutoCriacaoDTO;
import school.sptech.apimiseenplace.entity.CEP;
import school.sptech.apimiseenplace.entity.Pedido;
import school.sptech.apimiseenplace.entity.PedidoFesta;

import java.util.ArrayList;
import java.util.List;

public class PedidoFestaMapper {
    public static PedidoFesta toEntity(PedidoFestaCriacaoDTO pedidoFestaCriacaoDTO) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        PedidoFesta pedidoFesta = new PedidoFesta();

        String url = String.format("https://viacep.com.br/ws/%s/json/", pedidoFestaCriacaoDTO.getCep());

        var result = restTemplate.getForObject(url, String.class);
        CEP cep = objectMapper.readValue(result, CEP.class);

        pedidoFesta.setCliente(pedidoFestaCriacaoDTO.getCliente());
        pedidoFesta.setProdutos(new ArrayList<>());
        pedidoFesta.setDataEntrega(pedidoFestaCriacaoDTO.getDataEntrega());
        pedidoFesta.setCep(cep);

        return pedidoFesta;
    }

    public static PedidoFestaListagemDTO toDto(PedidoFesta pedidoFesta) {
        PedidoFestaListagemDTO pedidoFestaListagemDTO = new PedidoFestaListagemDTO();

        pedidoFestaListagemDTO.setId(pedidoFesta.getId());
        pedidoFestaListagemDTO.setCliente(pedidoFesta.getCliente());
        pedidoFestaListagemDTO.setProdutos(pedidoFesta.getProdutos());
        pedidoFestaListagemDTO.setDataEntrega(pedidoFesta.getDataEntrega());
        pedidoFestaListagemDTO.setValorTotal(0.0);
        pedidoFestaListagemDTO.setCep(pedidoFesta.getCep());

        return pedidoFestaListagemDTO;
    }

    public static PedidoFestaCriacaoDTO toCriacaoDto(PedidoFesta pedidoFesta){
        if (pedidoFesta == null) return null;

        PedidoFestaCriacaoDTO dto = new PedidoFestaCriacaoDTO();
        dto.setCep(pedidoFesta.getCep().toString());
        dto.setCliente(pedidoFesta.getCliente());
        dto.setDataEntrega(pedidoFesta.getDataEntrega());
        return dto;
    }

    public static List<PedidoFestaListagemDTO> toDto(List<PedidoFesta> pedidoFestas) {
        List<PedidoFestaListagemDTO> pedidoFestaListagemDTOS = new ArrayList<>();

        for (PedidoFesta pedidoFesta : pedidoFestas) {
            pedidoFestaListagemDTOS.add(toDto(pedidoFesta));
        }

        return pedidoFestaListagemDTOS;
    }
}
