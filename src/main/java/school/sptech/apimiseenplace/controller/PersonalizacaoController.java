package school.sptech.apimiseenplace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.apimiseenplace.dto.recheio.RecheioListagemDto;
import school.sptech.apimiseenplace.service.PersonalizacaoService;

import java.util.List;

@RestController
@RequestMapping("/personalizacoes")
@RequiredArgsConstructor
public class PersonalizacaoController {

    private final PersonalizacaoService service;

    @GetMapping
    public ResponseEntity<List<RecheioListagemDto.PersonalizacaoListagem>> listar(){
        List<RecheioListagemDto.PersonalizacaoListagem> personalizacoes = service.listar();

        if (personalizacoes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(personalizacoes);
    }
}
