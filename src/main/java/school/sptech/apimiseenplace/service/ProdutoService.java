package school.sptech.apimiseenplace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.apimiseenplace.exception.BadRequestException;
import school.sptech.apimiseenplace.exception.NaoEncontradoException;
import school.sptech.apimiseenplace.dto.produto.ProdutoCriacaoDTO;
import school.sptech.apimiseenplace.dto.produto.ProdutoListagemDTO;
import school.sptech.apimiseenplace.dto.produto.ProdutoMapper;
import school.sptech.apimiseenplace.entity.Pedido;
import school.sptech.apimiseenplace.entity.Produto;
import school.sptech.apimiseenplace.repository.ProdutoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    private final PedidoService pedidoService;

    public ProdutoListagemDTO cadastrarProduto(ProdutoCriacaoDTO produtoCriacao){
        if(produtoCriacao == null) throw new BadRequestException("Produto de Criação");

        Pedido pedido = pedidoService.encontrarPorId(produtoCriacao.getPedidoId());
        if (pedido == null) throw new NaoEncontradoException("Pedido OPT");

        Produto produto = ProdutoMapper.toEntity(produtoCriacao, pedido);
        produto = repository.save(produto);

        return ProdutoMapper.toListagemDto(produto);
    }

    public List<ProdutoListagemDTO> listarProdutos(){
        List<Produto> produtos = repository.findAll();
        return ProdutoMapper.toListagemDto(produtos);
    }

    public void deletarProdutoPorId(Integer id){
        if(!repository.existsById(id)){
            throw new BadRequestException("Produto");
        }

        repository.deleteById(id);
    }

}
