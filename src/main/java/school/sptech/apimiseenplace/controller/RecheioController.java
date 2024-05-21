package school.sptech.apimiseenplace.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apimiseenplace.dto.recheio.RecheioCriacaoDto;
import school.sptech.apimiseenplace.dto.recheio.RecheioListagemDto;
import school.sptech.apimiseenplace.dto.recheio.RecheioMapper;
import school.sptech.apimiseenplace.dto.recheio.RecheioSemPersonalizacaoDto;
import school.sptech.apimiseenplace.entity.Recheio;
import school.sptech.apimiseenplace.service.PersonalizacaoService;
import school.sptech.apimiseenplace.service.RecheioService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recheios")
@RequiredArgsConstructor
public class RecheioController {

    private final RecheioService service;
    private final PersonalizacaoService personalizacaoService;

    @PostMapping
    public ResponseEntity<RecheioListagemDto> criar(@RequestBody @Valid RecheioCriacaoDto recheioCriacaoDto){
        Recheio recheioSalvo = service.cadastrarRecheio(recheioCriacaoDto);
        return ResponseEntity.ok().body(RecheioMapper.toDto(recheioSalvo));
    }

    @GetMapping
    public ResponseEntity<List<RecheioSemPersonalizacaoDto>> listar(){
        List<Recheio> recheios = service.listarRecheios();
        if (recheios.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(RecheioMapper.toSemPersonalizacaoDto(recheios));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecheioListagemDto> atualizar(@PathVariable int id, @RequestBody @Valid RecheioCriacaoDto dtoAtualizacao){
        Recheio recheioAtualizado = service.atualizarRecheio(id, dtoAtualizacao);

        if (recheioAtualizado == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(RecheioMapper.toDto(recheioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id){

        List<RecheioListagemDto.PersonalizacaoListagem> listaPersonalizacoes = personalizacaoService.listarWhereIdRecheioEquals(id);

        if(!listaPersonalizacoes.isEmpty()){
            for(RecheioListagemDto.PersonalizacaoListagem p : listaPersonalizacoes){
                personalizacaoService.updateRecheio(null, p.getId());
            }
        service.deletarRecheioPorId(id);
        return ResponseEntity.status(204).body("Recheio Deletado com sucesso!");
        }

        return ResponseEntity.badRequest().build();
    }


}
