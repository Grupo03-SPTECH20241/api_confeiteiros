package school.sptech.apimiseenplace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apimiseenplace.dto.recheio.RecheioCriacaoDto;
import school.sptech.apimiseenplace.dto.recheio.RecheioMapper;
import school.sptech.apimiseenplace.entity.Recheio;
import school.sptech.apimiseenplace.exception.BadRequestException;
import school.sptech.apimiseenplace.exception.NaoEncontradoException;
import school.sptech.apimiseenplace.repository.RecheioRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecheioService {

    private final RecheioRepository repository;

    public Recheio cadastrarRecheio(RecheioCriacaoDto recheioCriacaoDto){
        if(recheioCriacaoDto == null) throw new BadRequestException("Recheio");
        return repository.save(RecheioMapper.toEntity(recheioCriacaoDto));
    }

    public List<Recheio> listarRecheios(){
        return repository.findAll();
    }

    public Recheio encontrarPorId(int id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Recheio")
        );
    }

    public Recheio atualizarRecheio(int id, RecheioCriacaoDto recheioCriacaoDto){
        Recheio recheioAchado = encontrarPorId(id);

        recheioAchado.setIdRecheio(id);
        recheioAchado.setNome(recheioCriacaoDto.getNome());
        recheioAchado.setPreco(recheioCriacaoDto.getPreco());

        return repository.save(recheioAchado);
    }

    public void deletarRecheioPorId(int id){
        if(!repository.existsById(id)) throw new BadRequestException("Recheio");

        repository.deleteById(id);
    }
}
