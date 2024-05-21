package school.sptech.apimiseenplace.dto.personalizacao;

import school.sptech.apimiseenplace.dto.cobertura.CoberturaSemPersonalizacaoDto;
import school.sptech.apimiseenplace.dto.massa.MassaSemPersonalizacaoDto;
import school.sptech.apimiseenplace.dto.recheio.RecheioListagemDto;
import school.sptech.apimiseenplace.dto.recheio.RecheioSemPersonalizacaoDto;
import school.sptech.apimiseenplace.entity.Cobertura;
import school.sptech.apimiseenplace.entity.Massa;
import school.sptech.apimiseenplace.entity.Personalizacao;
import school.sptech.apimiseenplace.entity.Recheio;

import java.util.ArrayList;
import java.util.List;

public class PersonalizacaoMapper {

    public static RecheioListagemDto.PersonalizacaoListagem toDtoSemPersonalizacao(Personalizacao entity) {
        RecheioListagemDto.PersonalizacaoListagem dto = new RecheioListagemDto.PersonalizacaoListagem();

        dto.setId(entity.getIdPersonalizacao());
        dto.setTema(entity.getTema());
        dto.setMassa(toMassaSemPersonalizacao(entity.getMassa()));
        dto.setCobertura(toCoberturaSemPersonalizacao(entity.getCobertura()));
        dto.setRecheio(toRecheioSemPersonalizacao(entity.getRecheio()));

        return dto;
    }

    public static List<RecheioListagemDto.PersonalizacaoListagem> toDtoSemPersonalizacao(List<Personalizacao> entities){
        return entities.stream().map(PersonalizacaoMapper::toDtoSemPersonalizacao).toList();
    }

    public static MassaSemPersonalizacaoDto toMassaSemPersonalizacao(Massa entitie) {
        if (entitie == null) return null;
        MassaSemPersonalizacaoDto dto = new MassaSemPersonalizacaoDto();
        dto.setIdMassa(entitie.getIdMassa());
        dto.setNome(entitie.getNome());

        return dto;
    }

    public static CoberturaSemPersonalizacaoDto toCoberturaSemPersonalizacao(Cobertura entitie) {
        if (entitie == null) return null;
        CoberturaSemPersonalizacaoDto dto = new CoberturaSemPersonalizacaoDto();
        dto.setIdCobertura(entitie.getIdCobertura());
        dto.setNome(entitie.getNome());

        return dto;
    }

    public static RecheioSemPersonalizacaoDto toRecheioSemPersonalizacao(Recheio entitie) {
        if (entitie == null) return null;
        RecheioSemPersonalizacaoDto dto = new RecheioSemPersonalizacaoDto();
        dto.setIdRecheio(entitie.getIdRecheio());
        dto.setNome(entitie.getNome());

        return dto;
    }
}
