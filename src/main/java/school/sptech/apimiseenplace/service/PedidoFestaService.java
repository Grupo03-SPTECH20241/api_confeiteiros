package school.sptech.apimiseenplace.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apimiseenplace.dto.pedidofesta.PedidoFestaCriacaoDTO;
import school.sptech.apimiseenplace.dto.pedidofesta.PedidoFestaMapper;
import school.sptech.apimiseenplace.entity.Pedido;
import school.sptech.apimiseenplace.entity.PedidoFesta;
import school.sptech.apimiseenplace.exception.BadRequestException;
import school.sptech.apimiseenplace.exception.NaoEncontradoException;
import school.sptech.apimiseenplace.repository.PedidoFestaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoFestaService {

    private final PedidoFestaRepository repository;

    public PedidoFesta cadastrarPedidoFesta(PedidoFestaCriacaoDTO pedidoCriacaoDTO) throws JsonProcessingException {
        if(pedidoCriacaoDTO == null) throw new BadRequestException("Pedido Festa Criação");
        return repository.save(PedidoFestaMapper.toEntity(pedidoCriacaoDTO));
    }

    public List<PedidoFesta> listarPedidosFesta(){
        return repository.findAll();
    }

    public PedidoFesta encontrarPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Pedido Festa")
        );
    }

    public void deletarPedidoFestaPorId(Integer id){
        if(!repository.existsById(id)){
            throw new BadRequestException("Pedido Festa");
        }

        repository.deleteById(id);
    }

    public boolean existsPorId(Integer id){
        return repository.existsById(id);
    }
}
