package school.sptech.apimiseenplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apimiseenplace.dto.pedidofesta.PedidoFestaCriacaoDTO;
import school.sptech.apimiseenplace.dto.pedidofesta.PedidoFestaListagemDTO;
import school.sptech.apimiseenplace.dto.pedidofesta.PedidoFestaMapper;
import school.sptech.apimiseenplace.dto.produto.ProdutoCriacaoDTO;
import school.sptech.apimiseenplace.dto.produto.ProdutoMapper;
import school.sptech.apimiseenplace.entity.PedidoFesta;
import school.sptech.apimiseenplace.entity.Produto;
import school.sptech.apimiseenplace.exception.BadRequestException;
import school.sptech.apimiseenplace.repository.PedidoFestaRepository;
import school.sptech.apimiseenplace.repository.ProdutoRepository;
import school.sptech.apimiseenplace.service.PedidoFestaService;
import school.sptech.apimiseenplace.service.ProdutoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos-festa")
public class PedidoFestaController {

    private final PedidoFestaService service;
    private final ProdutoService produtoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o Pedido Festa cadastrado!")
    })
    @PostMapping
    public ResponseEntity<PedidoFestaListagemDTO> criarPedidoFesta(
            @RequestBody @Valid PedidoFestaCriacaoDTO pedidoFestaCriacaoDTO
    ) throws JsonProcessingException {

        PedidoFesta pedidoFestaSalvo = service.cadastrarPedidoFesta(pedidoFestaCriacaoDTO);
        PedidoFestaListagemDTO pedidoFestaListagemDTO = PedidoFestaMapper.toDto(pedidoFestaSalvo);

        return ResponseEntity.ok(pedidoFestaListagemDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Produto fornecido esta vazio!", content = @Content)
    })
    @PostMapping("/adicionar-produto/{id}")
    public ResponseEntity<PedidoFestaListagemDTO> adicionarProduto(
            @RequestBody @Valid ProdutoCriacaoDTO produtoDTO,
            @PathVariable int id
    ) throws JsonProcessingException {
        PedidoFesta pedidoFesta = service.encontrarPorId(id);
        produtoService.cadastrarProduto(produtoDTO);

        pedidoFesta.adicionarProduto(ProdutoMapper.toEntity(produtoDTO));
        service.cadastrarPedidoFesta(PedidoFestaMapper.toCriacaoDto(pedidoFesta));

        PedidoFestaListagemDTO pedidoFestaListagemDTO = PedidoFestaMapper.toDto(pedidoFesta);
        return ResponseEntity.status(200).body(pedidoFestaListagemDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso!")
    })
    @GetMapping
    public ResponseEntity<List<PedidoFestaListagemDTO>> listar() {
        List<PedidoFesta> pedidos = service.listarPedidosFesta();
        if (pedidos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(PedidoFestaMapper.toDto(service.listarPedidosFesta()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido Festa deletado com sucesso!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido festa não encontrado com o id fornecido!", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        service.deletarPedidoFestaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
