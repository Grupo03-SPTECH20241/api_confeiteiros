package school.sptech.apimiseenplace.dto.recheio;

import org.springframework.security.core.parameters.P;
import school.sptech.apimiseenplace.dto.cobertura.CoberturaSemPersonalizacaoDto;
import school.sptech.apimiseenplace.dto.massa.MassaSemPersonalizacaoDto;
import school.sptech.apimiseenplace.dto.personalizacao.PersonalizacaoListagemDto;
import school.sptech.apimiseenplace.dto.produto.ProdutoCriacaoDTO;
import school.sptech.apimiseenplace.dto.produto_pedido.ProdutoPedidoListagemDTO;
import school.sptech.apimiseenplace.entity.Cobertura;
import school.sptech.apimiseenplace.entity.Personalizacao;
import school.sptech.apimiseenplace.entity.Recheio;

import java.util.ArrayList;
import java.util.List;

public class RecheioMapper {


    public static Recheio toEntity(RecheioCriacaoDto dto){

        Recheio entity = new Recheio();
        entity.setPreco(dto.getPreco());
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());

        return entity;
    }

    public static RecheioSemPersonalizacaoDto toSemPersonalizacaoDto(Recheio entity){

        RecheioSemPersonalizacaoDto dto = new RecheioSemPersonalizacaoDto();
        dto.setIdRecheio(entity.getIdRecheio());
        dto.setNome(entity.getNome());
        dto.setPreco(entity.getPreco());
        return dto;
    }

    public static List<RecheioSemPersonalizacaoDto> toSemPersonalizacaoDto(List<Recheio> entities){
        return entities.stream().map(RecheioMapper::toSemPersonalizacaoDto).toList();
    }

    public static RecheioListagemDto toDto(Recheio entity){

        RecheioListagemDto dto = new RecheioListagemDto();

        dto.setNome(entity.getNome());
        dto.setPreco(entity.getPreco());
        dto.setPersonalizacoes(toPersonalizacoesDto(entity.getPersonalizacao()));

        return dto;
    }

    public static List<RecheioListagemDto.PersonalizacaoListagem> toPersonalizacoesDto(List<Personalizacao> entities){
        if (entities == null) return null;
        List<RecheioListagemDto.PersonalizacaoListagem> dto = new ArrayList<>();

        for (Personalizacao p : entities){
            RecheioListagemDto.PersonalizacaoListagem p2 = new RecheioListagemDto.PersonalizacaoListagem();
            CoberturaSemPersonalizacaoDto c = new CoberturaSemPersonalizacaoDto();
            RecheioSemPersonalizacaoDto r = new RecheioSemPersonalizacaoDto();
            MassaSemPersonalizacaoDto m = new MassaSemPersonalizacaoDto();

            r.setIdRecheio(p.getRecheio().getIdRecheio());
            r.setNome(p.getRecheio().getNome());
            p2.setRecheio(r);

            p2.setTema(p.getTema());

            c.setNome(p.getCobertura().getNome());
            c.setIdCobertura(p.getCobertura().getIdCobertura());
            p2.setCobertura(c);

            m.setIdMassa(p.getMassa().getIdMassa());
            m.setNome(p.getMassa().getNome());
            p2.setMassa(m);

            dto.add(p2);
        }
        return dto;
    }

    public static List<RecheioListagemDto> toDto(List<Recheio> entities){
        return entities.stream().map(RecheioMapper::toDto).toList();
    }
}
