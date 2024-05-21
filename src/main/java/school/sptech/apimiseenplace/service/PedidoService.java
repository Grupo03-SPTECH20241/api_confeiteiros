package school.sptech.apimiseenplace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apimiseenplace.dto.pedido.PedidoCriacaoDTO;
import school.sptech.apimiseenplace.dto.pedido.PedidoMapper;
import school.sptech.apimiseenplace.exception.BadRequestException;
import school.sptech.apimiseenplace.exception.NaoEncontradoException;
import school.sptech.apimiseenplace.entity.Pedido;
import school.sptech.apimiseenplace.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public Pedido cadastrarPedido(PedidoCriacaoDTO pedidoCriacaoDTO){
        if(pedidoCriacaoDTO == null) throw new BadRequestException("Pedido Criação");
        return repository.save(PedidoMapper.toEntity(pedidoCriacaoDTO));
    }
    
    public List<Pedido> listarPedidos(){
        return repository.findAll();
    }
    
    public Pedido encontrarPorId(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Pedido")
        );
    }
    
    public void deletarPedidoPorId(Integer id){
        if(!repository.existsById(id)){
            throw new BadRequestException("Pedido");
        }
        
        repository.deleteById(id);
    }

    public Pedido[] getVectorPedido() {
        List<Pedido> pedidosList = repository.findAll();
        Pedido[] pedidos = new Pedido[repository.findAll().size()];

        for (int i = 0; i < pedidosList.size(); i++) {
            Optional<Pedido> pedido = repository.findById(i + 1);
            pedidos[i] = pedidosList.get(i);
        }

        return pedidos;
    }
}
